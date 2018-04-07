package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/5 16:19  <p />
 * Version: V1.0  <p />
 * Description: 产生死锁 <p />
 */
public class DeadLock implements Runnable {

    private Object obj1;

    private Object obj2;

    public DeadLock(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("Hellow");
            }
        }
    }

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(new DeadLock(obj1, obj2)).start();
        new Thread(new DeadLock(obj2, obj1)).start();
    }

}
