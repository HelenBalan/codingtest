package com.elenabalan.paysafetest.controller;

import com.elenabalan.paysafetest.model.MonitoringLogInfo;
import com.elenabalan.paysafetest.model.StartQuery;
import com.elenabalan.paysafetest.model.StopOrInfoQuery;
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
    public void stopMonitoring(@RequestBody StopOrInfoQuery stopOrInfoQuery) {
        monitoringService.stop(stopOrInfoQuery.getUri());
    }

    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public MonitoringLogInfo getOverview(@RequestBody StopOrInfoQuery stopOrInfoQuery) {
        return monitoringService.getOverview(stopOrInfoQuery.getUri());
    }
}

