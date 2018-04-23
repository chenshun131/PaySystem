package com.chenshun.test.lock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 21:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class AQSLockDemo {

    private int value;

    private AQSLock lock = new AQSLock();

    public int next() {
        lock.lock();
        try {
            Thread.sleep(300);
            return value++;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    public void a() {
        lock.lock();
        try {
            System.out.println("a");
            b();
        } finally {
            lock.unlock();
        }
    }

    public void b() {
        lock.lock();
        try {
            System.out.println("b");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AQSLockDemo m = new AQSLockDemo();
        new Thread(m::a).start();
    }

}
