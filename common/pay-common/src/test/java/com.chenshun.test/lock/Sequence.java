package com.chenshun.test.lock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/21 07:52  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Sequence {

    private MySpinLock lock = new MySpinLock();

    private int value;

    public int getNext() {
        lock.lock();
        value++;
        lock.unlock();
        return value;
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(s.getNext());
                }
            }).start();
        }
    }

}
