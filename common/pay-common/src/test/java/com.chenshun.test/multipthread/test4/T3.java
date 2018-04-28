package com.chenshun.test.multipthread.test4;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 20:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T3 {

    private ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    public int getNext() {
        Integer value = count.get();
        value++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {
        T3 d = new T3();
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
