package com.elenabalan.paysafetest.controller;

import com.elenabalan.paysafetest.model.MonitoringLogInfo;
import com.elenabalan.paysafetest.service.MonitoringService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PrepareForTest({MonitoringService.class})
public class MonitoringControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private MonitoringService monitoringService;

    private String fakeUri = "fakeuri.fake";
    private double seconds = 10;
    private String startStringQuery = "{\"uri\":\""+fakeUri+"\", \"seconds\": "+seconds+"}";
    private String stopOrInfoStringQuery = "{\"uri\":\""+fakeUri+"\"}";

    @Before
    public void setUp() {
        this.restTemplate = new TestRestTemplate();
    }
    @Test
    public void startMonitoring() throws Exception{

        RequestEntity<String> requestEntity = RequestEntity.put(new URI("http://localhost:"+port+"/monitor/start"))
                .contentType(MediaType.APPLICATION_JSON).body(startStringQuery);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Mockito.verify(monitoringService).start(fakeUri, seconds);
     }

    @Test
    public void stopMonitoring() throws URISyntaxException {
        RequestEntity<String> requestEntity = RequestEntity.put(new URI("http://localhost:"+port+"/monitor/stop"))
                .contentType(MediaType.APPLICATION_JSON).body(startStringQuery);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Mockito.verify(monitoringService).stop(fakeUri);
    }

    @Test
    public void getOverview() throws URISyntaxException {
        RequestEntity<String> requestEntity = RequestEntity.put(new URI("http://localhost:"+port+"/monitor/info"))
                .contentType(MediaType.APPLICATION_JSON).body(startStringQuery);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Mockito.verify(monitoringService).getOverview(fakeUri);
    }
}