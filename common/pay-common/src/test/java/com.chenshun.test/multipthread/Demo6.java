package com.chenshun.test.multipthread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: mew <p />
 * Time: 18/4/10 09:47  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo6 {

    @Test
    public void test1() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        threadPool.shutdown();
    }

    @Test
    public void test2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        threadPool.shutdown();
    }

}
