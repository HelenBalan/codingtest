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

package com.elenabalan.paysafetest.model;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *  Model of monitoring process
 */

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
