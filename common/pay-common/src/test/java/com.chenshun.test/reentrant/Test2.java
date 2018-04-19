package com.chenshun.test.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/19 23:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test2 implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test2 ss = new Test2();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
        // 打印
        // 11
        // 11
        // 12
        // 12
        // 13
        // 13
    }

}
