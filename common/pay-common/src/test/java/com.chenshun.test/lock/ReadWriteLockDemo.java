package com.chenshun.test.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 23:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ReadWriteLockDemo {

    private Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private Lock r = rwl.readLock();

    private Lock w = rwl.writeLock();

    private volatile boolean isUpdate;

    public void readWrite() {
        r.lock(); // 为了保证isUpdate能够拿到最新的值
        if (isUpdate) {
            r.unlock();
            w.lock();
            map.put("xxx", "xxx");
            r.lock();
            w.unlock();
        }

        Object obj = map.get("xxx");

        System.out.println(obj);
        r.unlock();

    }

    public Object get(String key) {
        r.lock();
        System.out.println(Thread.currentThread().getName() + " 读操作在执行..");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " 读操执行完毕..");
        }
    }

    public void put(String key, Object value) {
        w.lock();
        System.out.println(Thread.currentThread().getName() + " 写操作在执行..");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕..");
        }
    }

}
