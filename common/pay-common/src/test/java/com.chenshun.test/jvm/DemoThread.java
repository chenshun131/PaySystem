package com.chenshun.test.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 15:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DemoThread {

    static class Hello {

        static {
            System.out.println(Thread.currentThread().getName() + " init");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int i = 0;
        while (i++ < 20) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " start..");
                    Hello h = new Hello();
                    System.out.println(Thread.currentThread().getName() + " end..");
                }
            });
        }
    }

}
