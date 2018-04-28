package com.chenshun.test.multipthread.test4;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 21:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T5 {

    private Random random = new Random();

    public void meeting(CyclicBarrier barrier) {
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会..");

        if (Thread.currentThread().getName().equals("Thread-7")) {
            // Thread.currentThread().interrupt();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            barrier.reset();
        }
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T5 demo = new T5();
        CyclicBarrier barrier = new CyclicBarrier(10, () -> System.out.println("好！我们开始开会..."));
        for (int i = 0; i < 10; i++) {
            new Thread(() -> demo.meeting(barrier)).start();
        }

        // 监控等待线程数
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("等待的线程数 " + barrier.getNumberWaiting());
                System.out.println("is broken " + barrier.isBroken());
            }
        }).start();
    }

}
