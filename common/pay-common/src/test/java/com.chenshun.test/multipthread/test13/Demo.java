package com.chenshun.test.multipthread.test13;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: mew <p />
 * Time: 18/4/28 17:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 50, 10, TimeUnit.DAYS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                count.getAndIncrement();
            });
        }
        threadPool.shutdown();
        while (Thread.activeCount() > 1) {
        }
        System.out.println(count.get());
    }

}
