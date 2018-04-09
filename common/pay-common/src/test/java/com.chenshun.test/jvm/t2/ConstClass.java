package com.chenshun.test.jvm.t2;

/**
 * User: mew <p />
 * Time: 18/4/8 17:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ConstClass {

    public static final String HELLOWORLD = "Hello World";

    static {
        System.out.println("ConstCLass init");
    }

}
