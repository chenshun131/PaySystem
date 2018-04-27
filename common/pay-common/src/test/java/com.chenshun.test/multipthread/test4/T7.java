package com.chenshun.test.multipthread.test4;

import java.util.concurrent.Semaphore;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 21:21  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T7 {

    public void method(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is run ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }

    public static void main(String[] args) {
        T7 d = new T7();
        Semaphore semaphore = new Semaphore(10);
        while (true) {
            new Thread(() -> {
                d.method(semaphore);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
