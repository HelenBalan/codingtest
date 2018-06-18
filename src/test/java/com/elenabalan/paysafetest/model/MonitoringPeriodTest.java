package com.elenabalan.paysafetest.model;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

public class MonitoringPeriodTest {

    private MonitoringPeriod period = new MonitoringPeriod(Instant.now(),MonitoringState.READY);

    @Test
    public void setState() {
        period.setState(MonitoringState.UNAVAILABLE);
        assertEquals(MonitoringState.UNAVAILABLE, period.getState());
    }

    @Test
    public void setStart() {
        Instant instant = Instant.now();
        period.setStart(instant);
        assertEquals(instant, period.getStart());
    }
}