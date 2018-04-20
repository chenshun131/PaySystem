package com.chenshun.test.multipthread;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
