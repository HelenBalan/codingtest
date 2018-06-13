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

package com.elenabalan.paysafetest.service;

import com.elenabalan.paysafetest.model.MonitoringFlag;
import com.elenabalan.paysafetest.model.MonitoringLogInfo;
import com.elenabalan.paysafetest.model.MonitoringModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MonitoringService {

    private Map<String, MonitoringModel> monitoringData = new HashMap<>();

    /**
     *
     * @param uri  watched server
     * @return information about availability
     */

    public MonitoringLogInfo getOverview(String uri) {
        MonitoringModel monitor = monitoringData.get(uri);
        if (monitor == null) return null;
        return new MonitoringLogInfo(uri, monitor.getDuration().toMillis() / 1000.0, monitor.getPeriods());
    }

    /**
     *
     * @param uri watched server
     * @param seconds checking interval
     */
    public synchronized void start(String uri, double seconds) {
        MonitoringModel monitor = monitoringData.get(uri);
        if (monitor != null) {
            monitor.setFlag(MonitoringFlag.STOPPED);
        }
        monitor = new MonitoringModel(uri);
        monitor.setDuration(seconds);
        monitoringData.put(uri, monitor);
        MonitoringServiceRunner runner = new MonitoringServiceRunner(monitor);
        Thread thread = new Thread(runner, uri);
        thread.start();

    }

    /**
     *
     * @param uri watched server
     */
    public synchronized void stop(String uri) {

        MonitoringModel monitor = monitoringData.get(uri);
        if (monitor == null) {
            return;
        }
        monitor.setFlag(MonitoringFlag.STOPPED);
    }
}
