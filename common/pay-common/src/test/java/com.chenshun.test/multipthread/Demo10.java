package com.chenshun.test.multipthread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo10 extends RecursiveTask<Integer> {

    private int begin;

    private int end;

    public Demo10(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " ... ");
        int sum = 0;
        // 拆分任务
        if (end - begin <= 2) {
            // 计算
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            // 拆分
            Demo10 d1 = new Demo10(begin, (begin + end) / 2);
            Demo10 d2 = new Demo10((begin + end) / 2 + 1, end);

            // 执行任务
            d1.fork();
            d2.fork();

            Integer a = d1.join();
            Integer b = d2.join();
            sum = a + b;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool(3);
        Future<Integer> future = pool.submit(new Demo10(1, 1000000000));
        System.out.println("....");
        System.out.println("计算的值为：" + future.get());
    }

}
