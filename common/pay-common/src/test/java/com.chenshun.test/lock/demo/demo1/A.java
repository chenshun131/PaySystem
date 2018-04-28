package com.chenshun.test.lock.demo.demo1;

/**
 * User: chenshun131 <p />
 * Time: 18/4/24 22:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class A implements Runnable {

    private Demo demo;

    public A(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (true) {
            demo.a();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}