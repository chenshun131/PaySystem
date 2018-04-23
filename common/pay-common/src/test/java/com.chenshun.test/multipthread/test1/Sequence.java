package com.chenshun.test.multipthread.test1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: mew <p />
 * Time: 18/4/20 11:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Sequence {

    private int value;

    Lock lock = new ReentrantLock();

    Lock l1 = new ReentrantLock();

    /**
     * @return
     */
    public int getNext() {
        lock.lock();
        int a = value++;
        lock.unlock();
        return a;
    }

    public static void runMethad(Sequence s) {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " " + s.getNext());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        new Thread(() -> runMethad(s)).start();
        new Thread(() -> runMethad(s)).start();
        new Thread(() -> runMethad(s)).start();
    }

}
