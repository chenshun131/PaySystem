package com.chenshun.test.string;

/**
 * User: mew <p />
 * Time: 18/1/19 15:33  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T2 {

    public static final String A; // 常量A

    public static final String B; // 常量B

    static {
        A = "ab";
        B = "cd";
    }

    public static void main(String[] args) {
        // 将两个常量用+连接对s进行初始化
        String s = A + B;
        String t = "abcd";

        // 最终结果 : s不等于t，它们不是同一个对象
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        } else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
    }

}
