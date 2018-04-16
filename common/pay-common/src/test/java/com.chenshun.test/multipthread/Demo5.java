package com.chenshun.test.multipthread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 18/4/10 09:42  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo5 {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 实现定时任务
                System.out.println("Timer task is run");
            }
        }, 0, 1000);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
    }

}
