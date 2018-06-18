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

import java.io.Serializable;
import java.time.Instant;

/**
 * Time period with same state
 */
public class MonitoringPeriod implements Serializable {

    private MonitoringState state;
    private long start;
    private long end;

    MonitoringPeriod(Instant instantStart, MonitoringState state) {
        this.start = instantStart.toEpochMilli();
        this.end = this.start;
        this.state = state;
    }

    public MonitoringState getState() {
        return state;
    }

    public void setState(MonitoringState state) {
        this.state = state;
    }

    public Instant getStart() {
        return Instant.ofEpochMilli(start);
    }

    public void setStart(Instant instantStart) {
        this.start = instantStart.toEpochMilli();
    }

    public Instant getEnd() {
        return Instant.ofEpochMilli(end);
    }

    public void setEnd(Instant instantEnd) {
        this.end = instantEnd.toEpochMilli();
    }
}
