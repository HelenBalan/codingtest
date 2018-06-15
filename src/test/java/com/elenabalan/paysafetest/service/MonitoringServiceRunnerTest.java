package com.elenabalan.paysafetest.service;

import com.elenabalan.paysafetest.model.MonitoringFlag;
import com.elenabalan.paysafetest.model.MonitoringModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RestTemplate.class})
public class MonitoringServiceRunnerTest {

    private String fakeUri = "fakeuri.fake";
    @Test
    public void run() throws Exception{
        RestTemplate mockRest;

        mockRest = PowerMockito.mock(RestTemplate.class);
        PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(mockRest);

        when(mockRest.getForEntity(anyObject(),anyObject())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        MonitoringModel monitor = new MonitoringModel(fakeUri);
        monitor.setDuration(10);

        MonitoringServiceRunner runner = new MonitoringServiceRunner(monitor);
        Thread thread = new Thread(runner, fakeUri);
        thread.start();
        assertTrue(thread.isAlive());
        monitor.setFlag(MonitoringFlag.STOPPED);

        Thread.sleep(monitor.getDuration().toMillis());

        assertFalse(thread.isAlive());
    }
}