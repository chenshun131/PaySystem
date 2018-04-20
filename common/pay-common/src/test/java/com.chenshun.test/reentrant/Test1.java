package com.chenshun.test.reentrant;

/**
 * User: chenshun131 <p />
 * Time: 18/4/19 23:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test1 implements Runnable {

    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test1 ss = new Test1();
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
