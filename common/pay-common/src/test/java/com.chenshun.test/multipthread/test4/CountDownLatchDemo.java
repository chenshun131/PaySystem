package com.chenshun.test.multipthread.test4;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 22:06  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch c = new CountDownLatch(3);
        System.out.println("main start");
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.countDown();
            }, "thread" + i).start();
        }
        try {
            c.await();
            System.out.println("main end time=>" + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // main start
        // thread1 start
        // thread2 start
        // thread3 start
        // thread2 end
        // thread3 end
        // thread1 end
        // main end time=>22:09:49.112
    }

}
