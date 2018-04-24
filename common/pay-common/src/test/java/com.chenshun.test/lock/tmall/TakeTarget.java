package com.chenshun.test.lock.tmall;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 23:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TakeTarget implements Runnable {

    private Tmall tmall;

    public TakeTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}