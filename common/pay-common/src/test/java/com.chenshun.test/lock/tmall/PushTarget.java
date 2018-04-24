package com.chenshun.test.lock.tmall;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 23:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class PushTarget implements Runnable {

    private Tmall tmall;

    public PushTarget(Tmall tmall) {
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
