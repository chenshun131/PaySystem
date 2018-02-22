package com.chenshun.test.multipthread;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * User: mew <p />
 * Time: 18/2/22 08:55  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    private static final int COUNT = 10000;

    @Test
    public void test1() throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(COUNT);
        Thread t = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
