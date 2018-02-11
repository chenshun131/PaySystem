package com.chenshun.test.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: chenshun131 <p />
 * Time: 18/2/11 21:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test01 {

    @Test
    public void test1() {
        Object obj = new Object();
        System.out.println("*****:" + obj.getClass().getClassLoader());
    }

    @Test
    public void test2() throws ClassNotFoundException {
        Class clazz = Class.forName("com.chenshun.test.jvm.Test01");
        System.out.println("*****:" + clazz.getClassLoader());
    }

    @Test
    public void test3() {
        Test01 test = new Test01();
        System.out.println("*****:" + test.getClass().getClassLoader());
        System.out.println("*****Parent:" + test.getClass().getClassLoader().getParent());
        System.out.println("*****Parent Parent:" + test.getClass().getClassLoader().getParent().getParent());
    }

    // -Xms1024m -Xmx1024m -XX:+PrintGCDetails
    @Test
    public void test4() {
        // 获取 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 返回 Java 虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
    }

    // -Xms8m -Xmx8m -XX:+PrintGCDetails
    @Test
    public void test5() {
        String str = "www.baidu.com";
        while (true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }
    }

    // 1MB
    byte[] byteArray = new byte[1024 * 1024];

    // -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
    @Test
    public void test6() {
        List<Test01> list = new ArrayList<>();
        try {
            for (int i = 1; i <= 40; i++) {
                list.add(new Test01());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
