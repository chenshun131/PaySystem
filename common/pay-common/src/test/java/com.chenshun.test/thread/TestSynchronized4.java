package com.chenshun.test.thread;

/**
 * User: mew <p />
 * Time: 18/3/7 09:10  <p />
 * Version: V1.0  <p />
 * Description: 适用对象锁后将会导致另外一个线程无法访问对象的非静态方法 <p />
 */
public class TestSynchronized4 {

    class Inner {

        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }

        private synchronized void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private void m4t1(Inner inner) {
        synchronized (inner) { // 使用对象锁
            inner.m4t1();
        }
    }

    private void m4t2(Inner inner) {
        inner.m4t2();
    }

    public static void main(String[] args) {
        final TestSynchronized4 myt3 = new TestSynchronized4();
        final Inner inner = myt3.new Inner();
        Thread t1 = new Thread(() -> myt3.m4t1(inner), "t1");
        Thread t2 = new Thread(() -> myt3.m4t2(inner), "t2");
        t1.start();
        t2.start();
        // 输出 :
//        t1 : Inner.m4t1()=4
//        t1 : Inner.m4t1()=3
//        t1 : Inner.m4t1()=2
//        t1 : Inner.m4t1()=1
//        t1 : Inner.m4t1()=0
//        t2 : Inner.m4t2()=4
//        t2 : Inner.m4t2()=3
//        t2 : Inner.m4t2()=2
//        t2 : Inner.m4t2()=1
//        t2 : Inner.m4t2()=0
    }

}
