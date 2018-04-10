package com.chenshun.test.multipthread;

import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 18/4/10 09:15  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo1 extends Thread {

    public Demo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()) {
            System.out.println(getName() + "正在执行线程...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1 = new Demo1("first-thread");
        Demo1 demo2 = new Demo1("second-thread");

        demo1.start();
        demo2.start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(10));

//        demo1.stop();
        demo1.interrupt();
    }

}
