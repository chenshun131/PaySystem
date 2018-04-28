package com.chenshun.test.multipthread.test4;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 20:52  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T2 {

    public void a(Thread joinThread) {
        System.out.println("方法a执行了...");
        joinThread.start();
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a方法执行完毕...");
    }

    public void b() {
        System.out.println("加塞线程开始执行....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("加塞线程执行完毕...");
    }

    public static void main(String[] args) {
        T2 demo = new T2();
        Thread joinThread = new Thread(demo::b);
        new Thread(() -> demo.a(joinThread)).start();
    }

}
