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

    @Test
    public void test7() {
        // s1、s2 指向的 "abc" 会在编译期存放在 运行时常量池中，在类加载后存放到，常量池中，而常量池中的数据
        // 是无序不重复的因此 "abc" 只有一份这就导致 s1 s2 指向同一个字符串那么 s1 s2 相等
        // 而 s3 使用 new 创建的字符串不会在字符串常量池中查找而会直接开辟新空间存放数据因此 s3 不等于 是 s1
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2); // true  由于 String 对象都会存放到常量池中
        String s3 = new String("abc");
        System.out.println(s1 == s3); // false
        System.out.println(s1 == s3.intern()); // String 的 intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池
    }

}
