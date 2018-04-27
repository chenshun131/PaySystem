package com.chenshun.test.multipthread.test6;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    private int a;

    private int b;

    private int c;

    public void a() {
        // 写后读
        // 读后写
        // 写后写

        b = 2;

        a = 1; // 写操作
        c = a; // 读操作
        b = c + a;

        System.out.println(b);
    }

    public static void main(String[] args) {
        new Demo().a();
    }

}
