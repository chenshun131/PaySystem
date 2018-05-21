package com.chenshun.test.threadfactory;

import java.util.concurrent.*;

/**
 * User: mew <p />
 * Time: 18/5/15 10:11  <p />
 * Version: V1.0  <p />
 * Description: 测试线程工厂 <p />
 */
public class TestThreadFactory {

    public static void main(String[] args) {
        // 创建线程(并发)池,自动伸缩(自动条件线程池大小)
        ExecutorService es = Executors.newCachedThreadPool(new WorkThreadFactory());
        // 同时并发5个工作线程
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        // 指示当所有线程执行完毕后关闭线程池和工作线程,如果不调用此方法,jvm不会自动关闭
        es.shutdown();
        try {
            // 等待线程执行完毕,不能超过2*60秒,配合shutDown
            es.awaitTermination(2 * 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService singleES = Executors.newSingleThreadExecutor(new WorkThreadFactory());
        ThreadPoolExecutor single = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new WorkThreadFactory());

        int nThreads = 10;
        ExecutorService fixedES = Executors.newFixedThreadPool(nThreads, new WorkThreadFactory());
        ThreadPoolExecutor fixed = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new WorkThreadFactory());

        ExecutorService cachedES = Executors.newCachedThreadPool(new WorkThreadFactory());
        ThreadPoolExecutor cached = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new WorkThreadFactory());
    }

}
