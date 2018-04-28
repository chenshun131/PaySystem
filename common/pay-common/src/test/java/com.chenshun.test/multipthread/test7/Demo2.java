package com.chenshun.test.multipthread.test7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo2 {

    private Lock lock = new ReentrantLock();

    public void a() {
        lock.lock();
        System.out.println("...");
        lock.unlock(); // 1
    }

    public void b() {
        lock.lock(); // 2
        System.out.println("...");
        lock.unlock();
    }

}
