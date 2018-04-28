package com.chenshun.test.multipthread.test10;

import java.util.concurrent.locks.StampedLock;

/**
 * User: mew <p />
 * Time: 18/4/28 16:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    private int balance;

    private StampedLock lock = new StampedLock();

    /**
     * 优化方式，修改数据
     *
     * @param value
     */
    public void conditionReadWrite(int value) {
        // 首先判断balance的值是否符合更新的条件
        long stamp = lock.readLock();
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if (writeStamp != 0) { // 成功转换成为写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else {
                // 没有转换成写锁，这里需要首先释放读锁，然后再拿到写锁
                lock.unlockRead(stamp);
                // 获取写锁
                stamp = lock.writeLock();
            }
        }
        lock.unlock(stamp);
    }

    /**
     * 优化方式，读取数据
     */
    public int optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int c = balance;
        // 这里可能会出现了写操作，因此要进行判断
        if (!lock.validate(stamp)) {
            // 要从新读取
            long readStamp = lock.readLock();
            c = balance;
            stamp = readStamp;
        }
        // 释放读锁
        lock.unlockRead(stamp);
        return c;
    }

    /**
     * 修改数据
     *
     * @param value
     */
    public void write(int value) {
        long stamp = lock.writeLock();
        balance += value;
        lock.unlockWrite(stamp);
    }

    /**
     * 读取数据
     */
    public int read() {
        long stamp = lock.readLock();
        lock.tryOptimisticRead();
        int c = balance;
        // 释放读锁
        lock.unlockRead(stamp);
        return c;
    }

}
