/*
 * Copyright 2018 Elena Balan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.elenabalan.paysafetest.controller;

import com.elenabalan.paysafetest.model.MonitoringLogInfo;
import com.elenabalan.paysafetest.model.StartQuery;
import com.elenabalan.paysafetest.model.StopOrInfoQuery;
import com.elenabalan.paysafetest.service.MonitoringService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/**
 *  REST Controller
 */
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

