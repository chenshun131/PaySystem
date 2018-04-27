package com.chenshun.test.multipthread.test4;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 21:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T6 {

    public static void main(String[] args) {
        int runner = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        final Random random = new Random();
        for (char runnerName = 'A'; runnerName <= 'C'; runnerName++) {
            final String rN = String.valueOf(runnerName);
            new Thread(() -> {
                long prepareTime = random.nextInt(10000) + 100;
                System.out.println(rN + "is preparing for time:" + prepareTime);
                try {
                    Thread.sleep(prepareTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(rN + "is prepared, waiting for others");
                    cyclicBarrier.await(); // 当前运动员准备完毕，等待别人准备好
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(rN + "starts running time=>" + LocalTime.now()); // 所有运动员都准备好了，一起开始跑
            }).start();
        }
        // Bis preparing for time:1285
        // Cis preparing for time:5695
        // Ais preparing for time:1231
        // Ais prepared, waiting for others
        // Bis prepared, waiting for others
        // Cis prepared, waiting for others
        // Cstarts running time=>21:16:05.047
        // Astarts running time=>21:16:05.047
        // Bstarts running time=>21:16:05.047
    }

}
