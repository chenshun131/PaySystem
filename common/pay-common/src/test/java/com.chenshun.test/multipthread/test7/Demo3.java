package com.chenshun.test.multipthread.test7;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo3 {

    public void a() {
        System.out.println("a"); // 1 启动另外一个线程的线程
        new Thread(() -> {
            System.out.println("b"); // 2
        }).start();
    }

}
