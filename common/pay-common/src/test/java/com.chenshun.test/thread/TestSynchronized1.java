package com.chenshun.test.thread;

/**
 * User: mew <p />
 * Time: 18/3/7 08:45  <p />
 * Version: V1.0  <p />
 * Description: 类的 非静态方法锁 和 内置锁是同一把锁 <p />
 */
public class TestSynchronized1 {

    public void test1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public synchronized void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        final TestSynchronized1 myt2 = new TestSynchronized1();
        Thread test1 = new Thread(myt2::test1, "test1");
        Thread test2 = new Thread(myt2::test2, "test2");
        test1.start();
        test2.start();
        // 输出 :
//        test1 : 4
//        test1 : 3
//        test1 : 2
//        test1 : 1
//        test1 : 0
//        test2 : 4
//        test2 : 3
//        test2 : 2
//        test2 : 1
//        test2 : 0
    }

}
