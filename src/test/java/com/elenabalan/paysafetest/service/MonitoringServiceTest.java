package com.elenabalan.paysafetest.service;

import com.elenabalan.paysafetest.model.MonitoringFlag;
import com.elenabalan.paysafetest.model.MonitoringLogInfo;
import com.elenabalan.paysafetest.model.MonitoringModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MonitoringService.class, MonitoringServiceRunner.class, Thread.class})
public class MonitoringServiceTest {

    private MonitoringService service;
    private MonitoringServiceRunner mockRunner;
    private String fakeUri1 = "www.fakeuri1.fake";
    private String fakeUri2 = "www.fakeuri2.fake";
    private String fakeUri3 = "www.fakeuri3.fake";

    @Before
    public void setUp() throws Exception {
        service = new MonitoringService();
        mockRunner = PowerMockito.mock(MonitoringServiceRunner.class);
        PowerMockito.whenNew(MonitoringServiceRunner.class).withAnyArguments().thenReturn(mockRunner);
    }

    @Test
    public void getOverview() {
    }

    @Test
    public void startStop() {
        service.start(fakeUri1, 5);
        service.start(fakeUri2, 10);
        MonitoringLogInfo info1 = service.getOverview(fakeUri1);
        MonitoringLogInfo info2 = service.getOverview(fakeUri2);
        MonitoringLogInfo info3 = service.getOverview(fakeUri3);
        assertEquals(fakeUri1, info1.getUri());
        assertEquals(5,info1.getInterval(),0.001);
        assertEquals(fakeUri2, info2.getUri());
        assertEquals(10,info2.getInterval(),0.001);
        service.start(fakeUri1,15);
        assertEquals(15,service.getOverview(fakeUri1).getInterval(),0.001);
        service.stop(fakeUri3);
        service.stop(fakeUri1);
        Map<String, MonitoringModel> data = Whitebox.getInternalState(service,"monitoringData");
        MonitoringModel model = data.get(fakeUri1);
        assertEquals(MonitoringFlag.STOPPED,model.getFlag());
    }

    @Test
    public void stop() {

    }
}