package com.chenshun.test.multipthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * User: mew <p />
 * Time: 18/4/10 09:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo4 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("正在进行紧张的计算......");
        Thread.sleep(3000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo4 d = new Demo4();
        FutureTask<Integer> task = new FutureTask<>(d);

        new Thread(task).start();

        System.out.println("我先干点别的...... ");
        long start = System.currentTimeMillis();
        Integer result = task.get();
        long end = System.currentTimeMillis();
        System.out.println("线程执行结果为 : " + result + " 花费时间 : " + (end - start));
    }

}
