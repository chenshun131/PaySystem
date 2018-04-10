package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/10 10:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Sequence {

    private int value;

    /**
     * synchronized 放在普通方法上，内置锁就是当前类的实例
     *
     * @return
     */
    public synchronized int getNext() {
        return value++;
    }

    /**
     * synchronized 修饰静态方法，获取的是类锁
     *
     * @return
     */
    public static synchronized int getPrevious() {
        return 0;
    }

    public int xx() {
        synchronized (Sequence.class) {
            if (value > 0) {
                return value;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
//		while(true) {
//			System.out.println(s.getNext());
//		}

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
