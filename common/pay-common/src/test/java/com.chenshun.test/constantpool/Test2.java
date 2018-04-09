package com.chenshun.test.constantpool;

/**
 * User: mew <p />
 * Time: 18/4/8 15:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test2 {

    public static final String A;

    public static final String B;

    static {
        A = "ab";
        B = "cd";
    }

    public static void main(String[] args) {
        // 将两个常量用+连接对s进行初始化
        String s = A + B;
        String t = "abcd";
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        } else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
        // 打印 s不等于t，它们不是同一个对象
    }

}
