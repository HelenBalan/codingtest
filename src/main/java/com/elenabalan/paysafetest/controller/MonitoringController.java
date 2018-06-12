package com.elenabalan.paysafetest.controller;

import com.elenabalan.paysafetest.model.MonitoringLogInfo;
import com.elenabalan.paysafetest.service.MonitoringService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/monitor")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @RequestMapping(value = "/start", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void startMonitoring(@RequestBody StartQuery startQuery) {
        monitoringService.start(startQuery.getUri(), startQuery.getSeconds());
    }

    @RequestMapping(value = "/stop", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void stopMonitoring(@RequestBody StopQuery stopQuery) {
        monitoringService.stop(stopQuery.getUri());
    }

    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public MonitoringLogInfo getOverview(@RequestBody StopQuery stopQuery) {
        return monitoringService.getOverview(stopQuery.getUri());
    }
}

class StartQuery {
    private String uri;
    private double seconds;

    public StartQuery(String uri, double seconds) {
        this.uri = uri;
        this.seconds = seconds;
    }

    public StartQuery() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}

class StopQuery {
    private String uri;

    public StopQuery(String uri) {
        this.uri = uri;
    }

    public StopQuery() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}