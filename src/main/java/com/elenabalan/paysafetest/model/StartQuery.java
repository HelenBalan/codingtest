package com.elenabalan.paysafetest.model;

public class StartQuery {
    private String uri;
    private double seconds;

    public StartQuery(String uri, double seconds) {
        this.uri = uri;
        this.seconds = seconds;
    }

    public StartQuery() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
