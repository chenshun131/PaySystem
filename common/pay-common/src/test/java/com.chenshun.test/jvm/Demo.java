package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 14:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    static {
        i = 0;
//        System.out.print(i); // illegal forward reference 这一步就出错了
    }

    public static int i = 1;

}
