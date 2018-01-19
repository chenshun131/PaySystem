package com.chenshun.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: chenshun131 <p />
 * Time: 17/12/18 20:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ThreadTest {

    @Test
    public void test1() {
        Double d1 = 1.2;
        Double d2 = 1.2;
        Assert.assertFalse(d1 == d2); // 输出false
    }

    @Test
    public void test2() {
        Float f1 = 1.2F;
        Float f2 = 1.2F;
        Assert.assertFalse(f1 == f2); // 输出false
    }

    @Test
    public void test3() {
        Byte b1 = 10;
        Byte b2 = 10;
        Assert.assertTrue(b1 == b2); // 输出true
    }

    @Test
    public void test4() {
        Short s1 = 10;
        Short s2 = 10;
        Assert.assertTrue(s1 == s2); // 输出true
    }

    @Test
    public void test5() {
        Integer i1 = 127;
        Integer i2 = 127;
        Assert.assertTrue(i1 == i2); // 输出true

        Integer i3 = 128;
        Integer i4 = 128;
        Assert.assertFalse(i3 == i4); // 输出false
    }

    @Test
    public void test6() {
        Long l1 = 127L;
        Long l2 = 127L;
        Assert.assertTrue(l1 == l2); // 输出true

        Long l3 = 128L;
        Long l4 = 128L;
        Assert.assertFalse(l3 == l4); // 输出false
    }

    public static void main(String[] args) {
        int loopTime = 100000;
        Integer i = 0;
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = String.valueOf(i);
        }
        System.out.println("String.valueOf()：" + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i.toString();
        }
        System.out.println("Integer.toString()：" + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i + "";
        }
        System.out.println("i + \"\"：" + (System.currentTimeMillis() - startTime) + "ms");
    }

}
