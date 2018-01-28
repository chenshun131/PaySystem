package com.chenshun.test;

import org.junit.Test;

/**
 * User: chenshun131 <p />
 * Time: 18/1/24 21:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class data13 {

    @Test
    public void T1() {
        int a = 129;
        int b = 128;
        System.out.println("a 和b 与的结果是：" + (a & b));
    }

    @Test
    public void T2() {
        int a = 129;
        int b = 128;
        System.out.println("a 和b 或的结果是：" + (a | b));
    }

    @Test
    public void t3() {
        int a = 2;
        System.out.println(String.format("a(%s) 非的结果是：%s(%s)", Integer.toBinaryString(a), ~a, Integer.toBinaryString(~a)));
    }

}
