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
/**
 *  Wrapper for incoming information for stopping monitoring or getting info
 */
public class StopOrInfoQuery {
    private String uri;

    public StopOrInfoQuery(String uri) {
        this.uri = uri;
    }

    public StopOrInfoQuery() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}