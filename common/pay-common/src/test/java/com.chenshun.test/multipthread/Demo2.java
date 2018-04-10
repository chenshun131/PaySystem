package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/10 09:27  <p />
 * Version: V1.0  <p />
 * Description: 作为线程任务存在 <p />
 */
public class Demo2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("thread running ...");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Demo2());
        thread.start();
    }

}
