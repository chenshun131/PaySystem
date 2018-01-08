package wusc.edu.pay.app.notify.test;

/**
 * User: mew <p />
 * Time: 18/1/8 14:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MyThreadPrinter implements Runnable {

    private String name;

    private final Object prev;

    private final Object self;

    private MyThreadPrinter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(name);
                    count--;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        new Thread(new MyThreadPrinter("A", c, a)).start();
        Thread.sleep(10);
        new Thread(new MyThreadPrinter("B", a, b)).start();
        Thread.sleep(10);
        new Thread(new MyThreadPrinter("C", b, c)).start();
        Thread.sleep(10);
    }

}
