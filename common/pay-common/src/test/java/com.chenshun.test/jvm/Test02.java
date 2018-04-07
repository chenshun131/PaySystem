package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/5 10:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test02 {

    private Object instance;

    public Test02() {
        byte[] m = new byte[20 * 1024 * 1024];
    }

    /**
     * -verbose:gc -XX:+PrintGCDetails
     *
     * @param args
     */
    public static void main(String[] args) {
        Test02 t1 = new Test02();
        Test02 t2 = new Test02();

        t1.instance = t2;
        t2.instance = t1;

        t1 = null;
        t2 = null;

        System.gc();
        // HotSopt 默认采用 parallel 垃圾收集器
    }

}
