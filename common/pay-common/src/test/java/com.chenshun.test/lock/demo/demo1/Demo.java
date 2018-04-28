package com.chenshun.test.lock.demo.demo1;

/**
 * User: chenshun131 <p />
 * Time: 18/4/24 22:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    private int signal;

    public synchronized void a() {
        while (signal != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        signal++;
        notifyAll();
    }

    public synchronized void b() {
        while (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        signal++;
        notifyAll();
    }

    public synchronized void c() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        signal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        A a = new A(d);
        B b = new B(d);
        C c = new C(d);

        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }

}
