package com.chenshun.test.multipthread.test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: mew <p />
 * Time: 18/4/20 11:35  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    private static boolean FAIR = false;

    private Lock lock = new ReentrantLock(FAIR);

    public void a() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "   a");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        Test t = new Test();
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                while (true) {
                    t.a();
                }
            }).start();
        }
        // FAIR = true，打印
        // Thread-0   a
        // Thread-1   a
        // Thread-2   a
        // Thread-3   a
        // Thread-4   a
        // Thread-5   a
        // Thread-0   a
        // Thread-1   a
        // Thread-2   a
        // Thread-3   a
        // Thread-4   a
        // FAIR = false，打印
        // Thread-0   a
        // Thread-0   a
        // Thread-0   a
        // Thread-0   a
        // Thread-0   a
        // Thread-0   a
    }

}
