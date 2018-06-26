package com.elenabalan.paysafetest.service;

import com.elenabalan.paysafetest.model.MonitoringFlag;
import com.elenabalan.paysafetest.model.MonitoringModel;
import com.elenabalan.paysafetest.model.MonitoringState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.lang.SuppressWarnings;

import java.time.Duration;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RestTemplate.class, MonitoringModel.class, MonitoringServiceRunner.class, Thread.class})
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
        MonitoringState state = Whitebox.getInternalState(runner, "state");
        assertEquals(MonitoringState.READY, state);
        Mockito.verify(mockMonitor, Mockito.times(2)).saveInfo(anyObject(), anyObject());
        Mockito.verify(mockMonitor, Mockito.times(3)).getFlag();
    }

    @Test
    public void runBadRequest(){
        PowerMockito.when(mockRest.getForEntity(fakeUri,String.class)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        runner.run();
        MonitoringState state = Whitebox.getInternalState(runner,"state");
        assertEquals(MonitoringState.UNAVAILABLE,state);
    }

    @Test
    public void runNoConnection() throws Exception {

        PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenThrow(new RuntimeException());
        runner.run();
        MonitoringState state = Whitebox.getInternalState(runner,"state");
        assertEquals(MonitoringState.UNAVAILABLE,state);
    }
    @Test
    @SuppressWarnings({"squid:S2925"}) // It's just mocked sleep, nothing to warn
    public void runSleepException() throws Exception {

        PowerMockito.when(mockMonitor.getDuration()).thenReturn(Duration.ofSeconds(60));
        PowerMockito.spy(Thread.class);
        PowerMockito.doThrow(new InterruptedException()).when(Thread.class);
        Thread.sleep(Mockito.anyLong());
        when(mockMonitor.getFlag()).thenReturn(MonitoringFlag.RUN).thenReturn(MonitoringFlag.STOPPED);
        runner.run();
        Mockito.verify(mockMonitor).setFlag(MonitoringFlag.STOPPED);
    }
    
    @Test
    @SuppressWarnings({"squid:S2925"}) // It's just mocked sleep, nothing to warn
    public void runSleep() throws Exception {

        PowerMockito.when(mockMonitor.getDuration()).thenReturn(Duration.ofSeconds(60));
        PowerMockito.spy(Thread.class);
        PowerMockito.doNothing().when(Thread.class);
        Thread.sleep(Mockito.anyLong());
        runner.run();
        PowerMockito.verifyStatic(Thread.class,times(2));
        Thread.sleep(anyLong());
    }

    @Test
    @SuppressWarnings({"squid:S2925"}) // It's just mocked sleep, nothing to warn
    public void runLongWaiting() throws Exception {

        PowerMockito.when(mockMonitor.getDuration()).thenReturn(Duration.ofSeconds(-60));
        PowerMockito.spy(Thread.class);
        PowerMockito.doNothing().when(Thread.class);
        Thread.sleep(Mockito.anyLong());
        runner.run();
        PowerMockito.verifyStatic(Thread.class,times(0));
        Thread.sleep(anyLong());
    }
}