package com.chenshun.test.threadfactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: mew <p />
 * Time: 18/5/15 10:05  <p />
 * Version: V1.0  <p />
 * Description: 构建线程封装类WorkThread,该类的功能主要是为了能够更好的管理线程而创建的 <p />
 */
public class WorkThread extends Thread {

    /** 线程执行目标 */
    private Runnable target;

    private AtomicInteger counter;

    public WorkThread(Runnable target, AtomicInteger counter) {
        this.target = target;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            target.run();
        } finally {
            int c = counter.getAndDecrement();
            System.out.println("terminate no " + c + " Threads");
        }
    }

}
