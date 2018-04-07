package com.chenshun.test.jvm;

import org.junit.Test;

/**
 * User: chenshun131 <p />
 * Time: 18/4/5 13:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test03 {

    @Test
    public void test() {
        // -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
        // -XX:+UseSerialGC 指定采用 Serial 垃圾收集器
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];
        byte[] b4 = new byte[4 * 1024 * 1024];
        System.gc();
    }

    @Test
    public void test1() {
        byte[] b1 = new byte[1 * 1024 * 1024];
    }

    public static void main(String[] args) {
        byte[] b1 = new byte[2 * 1024 * 1024];
    }

}
