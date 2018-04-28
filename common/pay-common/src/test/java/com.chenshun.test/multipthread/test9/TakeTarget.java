package com.chenshun.test.multipthread.test9;

/**
 * User: mew <p />
 * Time: 18/4/28 16:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TakeTarget implements Runnable {

    private Shop tmall;

    public TakeTarget(Shop tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.take();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
