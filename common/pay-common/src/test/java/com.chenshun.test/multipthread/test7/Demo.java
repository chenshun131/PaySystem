package com.chenshun.test.multipthread.test7;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    private int a;

    private int b;

    private int c;

    /**
     * 1 happens-before 2
     * 2 happens-before 3
     * 3 happens-before 4
     * ...
     */
    public void a() {
        a = 2;  // 1
        b = 10; // 2

        c = a + b; // 3

        System.out.println(c);  // 4
    }

    public static void main(String[] args) {
        new Demo().a();
    }

}
