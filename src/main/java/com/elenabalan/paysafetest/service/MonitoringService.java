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

    public MonitoringLogInfo getOverview(String uri) {
        MonitoringModel monitor = monitoringData.get(uri);
        if (monitor == null) return null;
        return new MonitoringLogInfo(uri, monitor.getDuration().toMillis() / 1000, monitor.getPeriods());
    }

    public synchronized void start(String uri, double seconds) {
        MonitoringModel monitor = monitoringData.get(uri);
        if (monitor != null) {
            monitor.setFlag(MonitoringFlag.STOPPED);
        }
        monitor = new MonitoringModel(uri);
        monitor.setDuration(seconds);
        monitoringData.put(uri, monitor);
        Thread thread = new Thread(monitor, uri);
        thread.start();

    }

    public synchronized void stop(String uri) {

        MonitoringModel monitor = monitoringData.get(uri);
        if (monitor == null) {
            return;
        }
        monitor.setFlag(MonitoringFlag.STOPPED);
    }
}
