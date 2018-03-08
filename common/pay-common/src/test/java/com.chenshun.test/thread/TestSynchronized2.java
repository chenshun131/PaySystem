package com.chenshun.test.thread;

/**
 * User: mew <p />
 * Time: 18/3/7 08:50  <p />
 * Version: V1.0  <p />
 * Description: 类锁 和 静态方法锁 是同一把锁 <p />
 */
public class TestSynchronized2 {

    public void test1() {
        synchronized (TestSynchronized2.class) {
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

    public static synchronized void test2() {
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
        final TestSynchronized2 myt2 = new TestSynchronized2();
        Thread test1 = new Thread(myt2::test1, "test1");
        Thread test2 = new Thread(TestSynchronized2::test2, "test2");
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
