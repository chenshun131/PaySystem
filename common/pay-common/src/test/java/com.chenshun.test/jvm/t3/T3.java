package com.chenshun.test.jvm.t3;

import java.util.Properties;

/**
 * User: mew <p />
 * Time: 18/4/28 11:27  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T3 {

    public static void main(String[] args) {
        Properties p = System.getProperties();
        p.list(System.out);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(System.getProperty("conf.location"));
        System.out.println(System.getenv("path"));
    }

}
