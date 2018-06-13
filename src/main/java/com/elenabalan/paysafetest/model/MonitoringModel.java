package com.elenabalan.paysafetest.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MonitoringModel {

    private final String serverUri;

    private volatile MonitoringFlag flag = MonitoringFlag.RUN;

    private Duration duration;

    private List<MonitoringPeriod> periods = new ArrayList<>();

    public MonitoringModel(String sUri) {
        this.serverUri = sUri;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public MonitoringFlag getFlag() {
        return flag;
    }

    public void setFlag(MonitoringFlag flag) {
        this.flag = flag;
    }

    public void setDuration(double seconds) {
        long millis = (long) (seconds * 1000);
        duration = Duration.ofMillis(millis);
    }

    public List<MonitoringPeriod> getPeriods() {
        return new ArrayList<>(periods);
    }

    public String getServerUri() {
        return serverUri;
    }

    public void saveInfo(Instant time, MonitoringState currentState) {

        if ((!periods.isEmpty()) && (periods.get(periods.size() - 1).getState() == currentState)) {
            periods.get(periods.size() - 1).setEnd(time);
        } else {
            periods.add(new MonitoringPeriod(time, currentState));
        }

    }
}
