package com.chenshun.test.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 18/4/18 17:54  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new Vector<>();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    for (int j = 0; j < 999999; j++) {
                        TimeUnit.SECONDS.sleep(1);
                        list.add(Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

}
