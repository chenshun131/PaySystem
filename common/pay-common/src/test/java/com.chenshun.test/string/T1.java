package com.chenshun.test.string;

import org.junit.Test;

/**
 * User: mew <p />
 * Time: 18/1/19 15:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    public static final String A = "ab"; // 常量A

    public static final String B = "cd"; // 常量B

    public static void main(String[] args) {
        String s = A + B; // 将两个常量用+连接对s进行初始化
        String t = "abcd";

        // 最终显示 => s等于t，它们是同一个对象
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        } else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
    }

    @Test
    public void test1() {
        String strOne = "hello";
        String strTwo = "hello";
        System.out.println(strOne == strTwo); // true
        System.out.println(strOne + "" == strTwo); // false

        Integer int1 = 127;
        Integer int2 = 127;
        System.out.println(int1 == int2); // true

        Integer int3 = 128;
        Integer int4 = 128;
        System.out.println(int3 == int4); // false

        Integer int5 = new Integer(127);
        Integer int6 = new Integer(127);
        System.out.println(int5 == int6); // false

        Integer int7 = new Integer(127);
        Integer int8 = new Integer(127);
        Integer int9 = new Integer(0);
        System.out.println(int7 + int9 == int8); // true
    }

    @Test
    public void test2() {
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        System.out.println(str3 == str4); // false

        String str5 = "string";
        System.out.println(str3 == str5); // true
    }

}
