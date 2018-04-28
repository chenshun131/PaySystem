package com.chenshun.test.multipthread.test9;

/**
 * User: mew <p />
 * Time: 18/4/28 16:04  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class PushTarget implements Runnable {

    private Shop tmall;

    public PushTarget(Shop tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.push();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
