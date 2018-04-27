package com.chenshun.test.lock.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/25 00:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MyQueue<E> {

    private Object[] obj;

    private int addIndex;
    private int removeIndex;
    private int queueSize;

    private Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public MyQueue(int count) {
        obj = new Object[count];
    }

    public void add(E e) {
        lock.lock();
        while (queueSize == obj.length) {
            try {
                addCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[addIndex] = e;

        if (++addIndex == obj.length) {
            addIndex = 0;
        }

        queueSize++;
        removeCondition.signal();
        lock.unlock();
    }

    public void remove() {
        lock.lock();

        while (queueSize == 0) {
            try {
                removeCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        obj[removeIndex] = null;

        if (++removeIndex == obj.length) {
            removeIndex = 0;
        }

        queueSize--;

        addCondition.signal();

        lock.unlock();
    }

}
