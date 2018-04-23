package com.chenshun.test.multipthread;

import java.util.Random;

/**
 * User: mew <p />
 * Time: 18/4/20 10:05  <p />
 * Version: V1.0  <p />
 * Description: 多个线程执行完毕之后，打印一句话，结束 <p />
 */
public class SpinLock {

    public static void exec() {
        System.out.println(Thread.currentThread().getName() + " 线程执行...");
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 线程执行完毕了...");
    }

    public static void main(String[] args) {

        new Thread(SpinLock::exec).start();
        new Thread(SpinLock::exec).start();
        new Thread(SpinLock::exec).start();
        new Thread(SpinLock::exec).start();
        new Thread(SpinLock::exec).start();

        while (Thread.activeCount() != 1) {
            // 自旋
        }
        System.out.println("所有的线程执行完毕了...");
    }

}
