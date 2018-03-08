package com.chenshun.test.thread;

/**
 * User: mew <p />
 * Time: 18/3/7 08:54  <p />
 * Version: V1.0  <p />
 * Description: 类锁 和 对象锁不是通一把锁 <p />
 */
public class TestSynchronized3 {

    public synchronized void test1() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
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
        final TestSynchronized3 myt2 = new TestSynchronized3();
        Thread test1 = new Thread(myt2::test1, "test1");
        Thread test2 = new Thread(TestSynchronized3::test2, "test2");
        test1.start();
        test2.start();
        // 输出 :
//        test1 : 4
//        test2 : 4
//        test2 : 3
//        test1 : 3
//        test2 : 2
//        test1 : 2
//        test2 : 1
//        test1 : 1
//        test1 : 0
//        test2 : 0
    }

}
