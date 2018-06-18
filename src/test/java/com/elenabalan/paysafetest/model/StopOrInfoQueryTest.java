package com.elenabalan.paysafetest.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StopOrInfoQueryTest {

    private String fakeUri = "fakeuri.fake";
    private StopOrInfoQuery stopOrInfoQuery;
    @Test
    public void getSetUri() {
        stopOrInfoQuery = new StopOrInfoQuery();
        assertNull(stopOrInfoQuery.getUri());
        stopOrInfoQuery.setUri(fakeUri);
        assertEquals(fakeUri,stopOrInfoQuery.getUri());
        stopOrInfoQuery = new StopOrInfoQuery(fakeUri);
        assertEquals(fakeUri,stopOrInfoQuery.getUri());
    }
}