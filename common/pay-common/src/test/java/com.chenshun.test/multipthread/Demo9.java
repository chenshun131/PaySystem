package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/20 10:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo9 {

    public volatile boolean run = false;

    public static void main(String[] args) {
        Demo9 d = new Demo9();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.err.println("执行了第 " + i + " 次");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            d.run = true;
        }).start();

        new Thread(() -> {
            while (!d.run) {
                // 不执行
            }
            System.err.println("线程2执行了...");
        }).start();
    }

}
