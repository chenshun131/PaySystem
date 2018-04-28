package com.chenshun.test.multipthread.test4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 22:13  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CalcTask extends RecursiveTask<Integer> {

    private int begin;

    private int end;

    public CalcTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    private static final int threshold = 2;

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " 开始 " + begin + " " + end);
        if (end - begin <= threshold) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 结束..." + begin + " " + end);
            return begin + end;
        }

        int middle = (begin + end) / 2;
        CalcTask s = new CalcTask(begin, middle);
        CalcTask l = new CalcTask(middle + 1, end);

        s.fork();
        l.fork();

        int a = s.join();
        int b = l.join();
        System.out.println(Thread.currentThread().getName() + " join " + a + " " + b);
        return a + b;
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool(20);
        CalcTask c = new CalcTask(1, 8);
        Future<Integer> f = pool.submit(c);
        System.out.println(f.get());
    }

}
