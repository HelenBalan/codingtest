package com.elenabalan.paysafetest.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MonitoringModelTest {

    private String fakeUri = "fakeuri.fake";
    private double fakeDoubleDuration = 10;
    private Duration fakeDuration = Duration.ofMillis((long)(1000*fakeDoubleDuration));
    private MonitoringModel model;

    @Before
    public void setUp() {
        model = new MonitoringModel(fakeUri);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getsetDurationDouble() {
        model.setDuration(fakeDoubleDuration);
        assertEquals(fakeDuration, model.getDuration());
    }

    @Test
    public void setgetFlag() {
        model.setFlag(MonitoringFlag.RUN);
        assertEquals(MonitoringFlag.RUN, model.getFlag());
    }

    @Test
    public void getsetDurationDuration() {
        model.setDuration(fakeDuration);
        assertEquals(fakeDuration, model.getDuration());
    }

    @Test
    public void getPeriods() {
        model.getPeriods();
    }

    @Test
    public void getServerUri() {
        assertEquals(model.getServerUri(), fakeUri);
    }

    @Test
    public void saveInfo() {
        Instant time1 = Instant.now();
        Instant time2 = time1.plusSeconds(60);
        Instant time3 = time2.plusSeconds(60);
        model.saveInfo(time1,MonitoringState.READY);
        model.saveInfo(time2, MonitoringState.READY);
        model.saveInfo(time3, MonitoringState.UNAVAILABLE);
        List<MonitoringPeriod> periods = model.getPeriods();

        assertEquals(2, periods.size());
        MonitoringPeriod period1 = periods.get(0);
        assertEquals(time1, period1.getStart());
        assertEquals(time2, period1.getEnd());
        assertEquals(MonitoringState.READY, period1.getState());
        MonitoringPeriod period2 = periods.get(1);
        assertEquals(time3, period2.getStart());
        assertEquals(time3, period2.getEnd());
        assertEquals(MonitoringState.UNAVAILABLE, period2.getState());

    }
}