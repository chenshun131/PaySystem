package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/10 10:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Target implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Target());
        Thread t2 = new Thread(new Target());

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }

}
