package com.chenshun.test.jvm;

/**
 * User: mew <p />
 * Time: 18/4/8 17:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SuperClass {

    public static int value = 123;

    static {
        System.out.println("SuperClass init");
    }

}
