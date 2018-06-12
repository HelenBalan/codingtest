package com.elenabalan.paysafetest.model;

import java.io.Serializable;
import java.time.Instant;

public class MonitoringPeriod implements Serializable {

    private MonitoringState state = MonitoringState.UNDEFINED;
    private Instant start;
    private Instant end;

    public MonitoringPeriod(Instant start, MonitoringState state) {
        this.start = start;
        this.end = start;
        this.state = state;
    }

    public MonitoringState getState() {
        return state;
    }

    public void setState(MonitoringState state) {
        this.state = state;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }
}
