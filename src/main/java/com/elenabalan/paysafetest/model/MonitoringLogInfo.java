package com.elenabalan.paysafetest.model;

import java.io.Serializable;
import java.util.List;

public class MonitoringLogInfo implements Serializable {
    private String uri;
    private double interval;
    private List<MonitoringPeriod> periods;

    public MonitoringLogInfo(String uri, double interval, List<MonitoringPeriod> periods) {
        this.uri = uri;
        this.interval = interval;
        this.periods = periods;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public List<MonitoringPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<MonitoringPeriod> periods) {
        this.periods = periods;
    }
}
