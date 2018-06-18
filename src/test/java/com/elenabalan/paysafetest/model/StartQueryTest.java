package com.elenabalan.paysafetest.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StartQueryTest {

    private String fakeUri = "fakeUri.fake";
    private double seconds = 10;

    @Test
    public void getSetUri() {
        StartQuery startQuery = new StartQuery();
        assertNull(startQuery.getUri());
        startQuery.setUri(fakeUri);
        assertEquals(fakeUri,startQuery.getUri());
    }

    @Test
    public void getSetSeconds() {
        StartQuery startQuery = new StartQuery(fakeUri,seconds);
        startQuery.setSeconds(20);
        assertEquals(20, (int)startQuery.getSeconds());
    }
}