package com.chenshun.test.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * User: chenshun131 <p />
 * Time: 18/4/19 23:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    private int count = 0;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            count++;
            return;
        }
        while (!owner.compareAndSet(null, current)) {
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count != 0) {
                count--;
            } else {
                owner.compareAndSet(current, null);
            }
        }
    }

}
