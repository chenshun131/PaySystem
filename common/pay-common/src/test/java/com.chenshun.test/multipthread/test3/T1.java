package com.chenshun.test.multipthread.test3;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: mew <p />
 * Time: 18/4/23 08:50  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    @Test
    public void test1() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        reentrantLock.unlock();
    }

}
