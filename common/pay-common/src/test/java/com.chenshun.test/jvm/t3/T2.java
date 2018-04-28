package com.chenshun.test.jvm.t3;

/**
 * User: mew <p />
 * Time: 18/4/28 11:08  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T2 {

    public static void test() {
        T2.test();
    }

    public static void main(String[] args) {
        // -Xss200k -verbose:gc
        T2.test();
    }

}
