package com.chenshun.test.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: mew <p />
 * Time: 18/5/15 10:10  <p />
 * Version: V1.0  <p />
 * Description: 实现线程工厂的方法 <p />
 */
public class WorkThreadFactory implements ThreadFactory {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        int c = atomicInteger.incrementAndGet();
        System.out.println("create no " + c + " Threads");
        // 通过计数器,可以更好的管理线程
        return new WorkThread(r, atomicInteger);
    }

}
