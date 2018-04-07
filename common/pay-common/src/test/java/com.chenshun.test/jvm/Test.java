package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 12:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    private static int a = 1; // <clinit>

    private int x = 1; // <init>

    public Test() {
        // <init>
    }

    static {
        // <clinit>
    }

}
