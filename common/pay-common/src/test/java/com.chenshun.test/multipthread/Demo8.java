package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/20 10:20  <p />
 * Version: V1.0  <p />
 * Description:
 * 保证可见性的前提 <br/>
 * 多个线程拿到的是同一把锁，否则是保证不了的 <p />
 */
public class Demo8 {

    public volatile int a = 1;

    public synchronized int getA() {
        return a++;
    }

    public synchronized void setA(int a) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {
        Demo8 demo = new Demo8();
        demo.a = 10;
        new Thread(() -> {
            System.out.println("a : " + demo.a);
            demo.a = 100;
        }).start();
        new Thread(() -> {
            System.out.println("b : " + demo.a);
            demo.a = 200;
        }).start();
        new Thread(() -> {
            System.out.println("c : " + demo.a);
            demo.a = 300;
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最终的结果为：" + demo.getA());
    }

}
