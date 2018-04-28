package com.chenshun.test.multipthread;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:30  <p />
 * Version: V1.0  <p />
 * Description: <p />
 * 程序次序规则 <p />
 * 监视器规则 <p />
 * 传递性 <p />
 */
public class Demo11 {

    private int value;

    public synchronized void a() { // 1  获取锁
        value++; // 2
    } // 3 释放锁

    public synchronized void b() { // 4 获取锁
        int a = value; // 5
        // 处理其他的操作
    } // 6 释放锁

}
