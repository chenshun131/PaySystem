package com.chenshun.test.multipthread.test10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * User: mew <p />
 * Time: 18/4/28 16:48  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class LongAdderDemo {

    private static final int MAX_THREADS = 10;

    private static final int TASK_COUNT = 10;

    private static final int TARGET_COUNT = 10000000;

    private AtomicLong acount = new AtomicLong(0L);

    private LongAdder lacount = new LongAdder();

    private long count = 0;

    private static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);

    private static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);

    private static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    protected synchronized long inc() {
        return ++count;
    }

    protected synchronized long getCount() {
        return count;
    }

    public class SyncThread implements Runnable {

        private String name;

        private LongAdderDemo out;

        public SyncThread(long starttime, LongAdderDemo out) {
            this.out = out;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while (v < TARGET_COUNT) {
                v = out.inc();
            }
            System.out.println("SyncThread v" + v);
            cdlsync.countDown();
        }

    }

    public void testSync() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(starttime, this);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(sync);
        }
        cdlsync.await();
        long endtime = System.currentTimeMillis();
        System.out.println("SyncThread spend:" + (endtime - starttime) + "ms");
        exe.shutdown();
    }

    public class AtomicThread implements Runnable {

        private String name;

        private long starttime;

        @Override
        public void run() {
            long v = acount.get();
            while (v < TARGET_COUNT) {
                v = acount.incrementAndGet();
            }
            System.out.println("AtomicThread v" + v);
            cdlatomic.countDown();
        }

    }

    public void testAtomic() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        AtomicThread atomic = new AtomicThread();
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        cdlatomic.await();
        long endtime = System.currentTimeMillis();
        System.out.println("AtomicThread spend:" + (endtime - starttime) + "ms");
        exe.shutdown();
    }

    public class LongAdderThread implements Runnable {

        private String name;

        private long starttime;

        public LongAdderThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v < TARGET_COUNT) {
                lacount.increment();
                v = lacount.sum();
            }
            System.out.println("LongAdderThread v" + v);
            cdladdr.countDown();
        }

    }

    public void testLongAdder() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        LongAdderThread atomic = new LongAdderThread(starttime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        cdladdr.await();
        long endtime = System.currentTimeMillis();
        System.out.println("LongAdderThread spend:" + (endtime - starttime) + "ms");
        exe.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testLongAdder();
    }

}
