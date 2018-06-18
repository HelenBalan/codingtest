package com.elenabalan.paysafetest.service;

import com.elenabalan.paysafetest.model.MonitoringFlag;
import com.elenabalan.paysafetest.model.MonitoringModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RestTemplate.class, MonitoringModel.class, MonitoringServiceRunner.class})
public class MonitoringServiceRunnerTest {

    private String fakeUri = "fakeuri.fake";
    private RestTemplate mockRest;
    private MonitoringModel mockMonitor;
    private MonitoringServiceRunner runner;

    @Before
    public void setUp() throws Exception {
        mockRest = PowerMockito.mock(RestTemplate.class);
        PowerMockito.when(mockRest.getForEntity(fakeUri,String.class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(mockRest);

        mockMonitor = PowerMockito.mock(MonitoringModel.class);
        when(mockMonitor.getDuration()).thenReturn(Duration.ofSeconds(1));
        when(mockMonitor.getServerUri()).thenReturn(fakeUri);
        when(mockMonitor.getFlag()).thenReturn(MonitoringFlag.RUN).thenReturn(MonitoringFlag.RUN).thenReturn(MonitoringFlag.STOPPED);
        runner = new MonitoringServiceRunner(mockMonitor);
    }

    @Test
    public void runNorm() {

        runner.run();
        PowerMockito.when(mockRest.getForEntity(fakeUri,String.class)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        runner.run();
    }

    @Test
    public void runBadRequest() {

        PowerMockito.when(mockRest.getForEntity(fakeUri,String.class)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        runner.run();
    }

    @Test
    public void runNoConnection() throws Exception {

        PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenThrow(new RuntimeException());
        runner.run();
    }
    @Test
    public void runSleepException() throws Exception {

        PowerMockito.when(mockMonitor.getDuration()).thenReturn(Duration.ofMillis(60));
        PowerMockito.spy(Thread.class);
        PowerMockito.doThrow(new InterruptedException()).when(Thread.class);
        Thread.sleep(Mockito.anyLong());
        runner.run();
    }
    @Test
    public void runLongWaiting() {

        PowerMockito.when(mockMonitor.getDuration()).thenReturn(Duration.ofMillis(-5));
        runner.run();
    }
}