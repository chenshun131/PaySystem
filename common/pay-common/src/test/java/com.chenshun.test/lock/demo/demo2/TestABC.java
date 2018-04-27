package com.chenshun.test.lock.demo.demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/24 23:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestABC {

    private static int LOOP_COUNT = 20;

    private int num = 1;

    private Lock lock = new ReentrantLock();

    private Condition condationA = lock.newCondition();

    private Condition condationB = lock.newCondition();

    private Condition condationC = lock.newCondition();

    private void a(int i) {
        lock.lock();
        while (num != 1) {
            try {
                condationA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(i + " : A");
        num++;
        condationB.signal();
        lock.unlock();
    }

    private void b() {
        lock.lock();
        while (num != 2) {
            try {
                condationB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        num++;
        condationC.signal();
        lock.unlock();
    }

    private void c() {
        lock.lock();
        while (num != 3) {
            try {
                condationC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        num = 1;
        condationA.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        TestABC testABC = new TestABC();

        new Thread(() -> {
            for (int i = 1; i <= LOOP_COUNT; i++) {
                testABC.a(i);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                testABC.b();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                testABC.c();
            }
        }).start();
    }

}
