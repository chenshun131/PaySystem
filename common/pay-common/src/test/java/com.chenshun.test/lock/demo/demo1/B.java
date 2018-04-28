package com.chenshun.test.lock.demo.demo1;

/**
 * User: chenshun131 <p />
 * Time: 18/4/24 22:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class B implements Runnable {

    private Demo demo;

    public B(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (true) {
            demo.b();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
