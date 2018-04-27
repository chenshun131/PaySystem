package com.chenshun.test.multipthread.test4;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 21:50  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T9 {

    /**
     * Callalbe和Runnable的区别
     * Runnable run方法是被线程调用的，在run方法是异步执行的
     * Callable的call方法，不是异步执行的，是由Future的run方法调用的
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Callable<Integer> call = () -> {
            System.out.println("正在计算结果...");
            Thread.sleep(3000);
            return 1;
        };

        FutureTask<Integer> task = new FutureTask<>(call);
        Thread thread = new Thread(task);
        thread.start();

        // do something
        System.out.println("干点别的...");
        System.out.println("拿到的结果为：" + task.get());
        // 干点别的...
        // 正在计算结果...
        // 拿到的结果为：1

    }

}
