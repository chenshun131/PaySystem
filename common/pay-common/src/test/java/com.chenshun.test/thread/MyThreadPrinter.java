package com.chenshun.test.thread;

/**
 * User: mew <p />
 * Time: 18/1/15 15:38  <p />
 * Version: V1.0  <p />
 * Description: 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC <p />
 */
public class MyThreadPrinter implements Runnable {

    private String name;

    private final Object prev;

    private final Object self;

    public MyThreadPrinter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int i = 10;
        while (i > 0) {
            synchronized (prev) {
                synchronized (self) {
                    if ("C".equals(name)) {
                        System.out.println(name);
                    } else {
                        System.out.print(name);
                    }
                    i--;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    self.notifyAll();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread(new MyThreadPrinter("A", c, a)).start();
        Thread.sleep(10);
        new Thread(new MyThreadPrinter("B", a, b)).start();
        Thread.sleep(10);
        new Thread(new MyThreadPrinter("C", b, c)).start();
    }

}
