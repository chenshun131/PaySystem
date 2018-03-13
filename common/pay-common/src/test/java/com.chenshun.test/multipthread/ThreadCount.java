package com.chenshun.test.multipthread;

import java.util.concurrent.*;

/**
 * User: chenshun131 <p />
 * Time: 18/3/13 21:31  <p />
 * Version: V1.0  <p />
 * Description: 假如有Thread1、Thread2、ThreaD3、Thread4四条线程分别统计C、D、E、F四个盘的大小，所有线程都统计完毕交给Thread5线程去做汇总，应当如何实现？<p />
 */
public class ThreadCount implements Callable<Integer> {

    private int type;

    public ThreadCount(int type) {
        this.type = type;
    }

    @Override
    public Integer call() throws Exception {
        if (type == 1) {
            System.out.println("C盘统计大小");
            return 1;
        } else if (type == 2) {
            Thread.sleep(20000);
            System.out.println("D盘统计大小");
            return 2;
        } else if (type == 3) {
            System.out.println("E盘统计大小");
            return 3;
        } else if (type == 4) {
            System.out.println("F盘统计大小");
            return 4;
        }
        return null;
    }

    public static void main(String[] args) {
        // 线程池
        ExecutorService es = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<>(es);
        for (int i = 1; i <= 4; i++) {
            cs.submit(new ThreadCount(i));
        }
        // 添加结束，及时shutdown，不然主线程不会结束
        es.shutdown();

        int total = 0;
        for (int i = 0; i < 4; i++) {
            try {
                total += cs.take().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(total);
    }

}
