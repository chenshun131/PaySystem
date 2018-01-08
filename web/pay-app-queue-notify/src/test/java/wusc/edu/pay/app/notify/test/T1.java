package wusc.edu.pay.app.notify.test;

import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 18/1/8 14:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 implements Runnable {

    private String name;

    private Object pre;

    private Object self;

    public T1(String name, Object pre, Object self) {
        this.name = name;
        this.pre = pre;
        this.self = self;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= 10) {
            synchronized (pre) {
                synchronized (self) {
                    System.out.println(name + " " + i++);
                    self.notifyAll();
                }
                try {
                    pre.wait();
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

        new Thread(new T1("A", c, a)).start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(new T1("B", a, b)).start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(new T1("C", b, c)).start();
    }

}
