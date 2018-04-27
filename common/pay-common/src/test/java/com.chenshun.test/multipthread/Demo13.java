package com.chenshun.test.multipthread;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo13 {

    private int a;

    private static final int b;

    static {
        b = 10;
    }

    public Demo13() { // 1
//		b = 20; // 2
        a = 10; // 3
    } // 4

    private Demo13 demo;

    public void w() { // 5
        demo = new Demo13(); // 6
    } //

    public void r() {
        Demo13 d = demo; // 7
        int temp1 = d.a; // 8
        int temp2 = d.b; // 9
    }

}
