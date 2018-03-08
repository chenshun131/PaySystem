package com.chenshun.test.thread;

/**
 * User: mew <p />
 * Time: 18/3/7 09:19  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestSynchronized5 {

    public synchronized void showA() {
        System.out.println("showA..");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showB() {
        synchronized (this) {
            System.out.println("showB..");
        }
    }

    public void showC() {
        String s = "1";
        synchronized (s) {
            System.out.println("showC..");
        }
    }

    public static void main(String[] args) {
        final TestSynchronized5 sy = new TestSynchronized5();
        new Thread(sy::showA).start();
        new Thread(sy::showB).start();
        new Thread(sy::showC).start();
        // 输出 :
//        showA..
//        showC..
//        showB..
    }

}
