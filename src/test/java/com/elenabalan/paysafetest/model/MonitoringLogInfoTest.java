package com.elenabalan.paysafetest.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MonitoringLogInfoTest {

    private String fakeUri = "www.fakeuri.fake";
    private String newUri = "www.newuri.fake";
    private double interval = 10;
    private List<MonitoringPeriod> periods = new ArrayList<>();
    private List<MonitoringPeriod> newPeriods = new ArrayList<>();

    private MonitoringLogInfo monitoringLogInfo = new MonitoringLogInfo(fakeUri, interval, periods);

    @Test
    public void setUri() {
        monitoringLogInfo.setUri(newUri);
        assertEquals(newUri,monitoringLogInfo.getUri());
    }

    @Test
    public void getSetPeriods() {
        monitoringLogInfo.setPeriods(newPeriods);
        assertEquals(newPeriods, monitoringLogInfo.getPeriods());
    }

}