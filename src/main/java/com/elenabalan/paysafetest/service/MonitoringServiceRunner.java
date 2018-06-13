package com.elenabalan.paysafetest.service;

import com.elenabalan.paysafetest.model.MonitoringFlag;
import com.elenabalan.paysafetest.model.MonitoringModel;
import com.elenabalan.paysafetest.model.MonitoringState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;

import static java.lang.Thread.sleep;

public class MonitoringServiceRunner implements Runnable{

    private MonitoringModel monitor;
    public MonitoringServiceRunner(MonitoringModel monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        Instant beginTimeMark;
        Instant endTimeMark;
        MonitoringState state;

        while (monitor.getFlag() == MonitoringFlag.RUN) {
            beginTimeMark = Instant.now();
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity(monitor.getServerUri(), String.class);
                state = response.getStatusCode() == HttpStatus.OK ? MonitoringState.READY : MonitoringState.UNAVAILABLE;
            } catch (final Exception e) {
                state = MonitoringState.UNAVAILABLE;
            }

            endTimeMark = Instant.now();
            monitor.saveInfo(endTimeMark, state);
            Duration responseTime = Duration.between(beginTimeMark, endTimeMark);
            Duration leftTime = monitor.getDuration().minus(responseTime);
            if (leftTime.isNegative()) continue;
            try {
                sleep(leftTime.toMillis());
            } catch (InterruptedException e) {
                monitor.setFlag(MonitoringFlag.STOPPED);
            }
        }
    }
}
