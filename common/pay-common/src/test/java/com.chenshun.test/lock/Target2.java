package com.chenshun.test.lock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 23:49  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Target2 implements Runnable {

    private VolatileDemo3 demo;

    public Target2(VolatileDemo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.get();
    }

}
