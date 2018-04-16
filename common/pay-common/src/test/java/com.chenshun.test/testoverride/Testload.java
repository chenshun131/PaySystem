package com.chenshun.test.testoverride;

/**
 * User: mew <p />
 * Time: 18/4/12 09:42  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Testload {

    public static void evaluate(String str) {
        System.out.println("字符串");
    }

    public static void evaluate(int i) {
        System.out.println("Int 类型");
    }

    public static void main(String[] args) {
        Testload.evaluate("String Test");
    }

}
