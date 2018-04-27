package com.chenshun.test.multipthread.test4;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 20:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("join 任务开始");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("join 任务结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        System.out.println("主线程 开始" + LocalTime.now());
        thread.join();
        System.out.println("主线程 结束" + LocalTime.now());
        // join 任务开始
        // 主线程 开始20:47:38.614
        // join 任务结束
        // 主线程 结束20:47:43.591
    }

}
