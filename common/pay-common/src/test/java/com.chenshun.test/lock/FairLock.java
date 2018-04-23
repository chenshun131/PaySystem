package com.chenshun.test.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 21:28  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class FairLock {

    private boolean isLocked = false;

    private Thread lockingThread = null;

    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
        try {
            queueObject.doWait();
        } catch (InterruptedException e) {
            synchronized (this) {
                waitingThreads.remove(queueObject);
            }
            throw e;
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }

}
