package com.elenabalan.paysafetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MainTest {
    @Test
    public void applicationStarts() {
        Main.main(new String[] {});
    }
}