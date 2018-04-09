package com.chenshun.test.jvm.t1;

/**
 * User: mew <p />
 * Time: 18/4/8 17:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SuperClass {

    public static int value = 123;

    static {
        System.out.println("SuperClass init");
    }

}
