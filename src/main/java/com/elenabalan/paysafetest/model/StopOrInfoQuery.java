package com.elenabalan.paysafetest.model;

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