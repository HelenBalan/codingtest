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

/*
 *  Outgoing information about monitoring
 *  */

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
