package com.chenshun.test.lock.queue;

/**
 * User: chenshun131 <p />
 * Time: 18/4/25 00:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    public void target (Thread joinThread) {

        System.out.println("target 方法执行了...");
        try {
            joinThread.start();
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("join 线程执行完毕...");

    }


    public static void main(String[] args) {
        Demo d = new Demo();

        Thread joinThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    d.a();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        new Thread(new Runnable() {

            @Override
            public void run() {
                d.target(joinThread);
            }
        }).start();
    }


    protected void a() throws InterruptedException {
        System.out.println("join 线程进入");
        Thread.sleep(1000);
    }

}
