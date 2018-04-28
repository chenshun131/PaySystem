package com.chenshun.test.other;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * User: mew <p />
 * Time: 18/4/28 10:13  <p />
 * Version: V1.0  <p />
 * Description: 生成 0 ~ 10 之间的整数 <p />
 */
public class T2 {

    @Test
    public void t1() {
        // 线程隔离的产生随机数
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " : " + ThreadLocalRandom.current().nextInt(10)));
        }
        executorService.shutdown();
    }

    @Test
    public void t2() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> System.out.println(Math.round(Math.random() * 10)));
        }
        executorService.shutdown();
    }

    @Test
    public void t3() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> System.out.println(new Random().nextInt(11)));
        }
        executorService.shutdown();
    }

}
