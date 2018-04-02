package com.chenshun.test.base;

/**
 * User: chenshun131 <p />
 * Time: 18/4/1 10:28  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    public final double i = Math.random();

    public static double j = Math.random();

    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        System.out.println("final test1.i = " + test1.i); // final test1.i = 0.508027890816311
        System.out.println("final test2.i = " + test2.i); // final test2.i = 0.4650830619109767
        System.out.println("static test1.j = " + test1.j); // static test1.j = 0.9228946092570839
        System.out.println("static test2.j = " + test2.j); // static test2.j = 0.9228946092570839
    }

    @org.junit.Test
    public void test1() {
        String a = "hello2";
        final String b = "hello";
        String d = "hello"; // 非final类型在编译阶段确定不
        String c = b + 2; // final类型，在编译阶段能够确定值，此时 c 直接从 String 池中取值，因此 a 和 c 是同一个 String 对象
        String e = d + 2;
        System.out.println(a == c); // true
        System.out.println(a == e); // false
    }

    @org.junit.Test
    public void test2() {
        String a = "AB";
        String b = "AB";
        String c = "A";
        String d = "B";
        String e = c + d;
        System.out.println(a == b); // true
        System.out.println(a == e); // false
    }

    public static String getHello() {
        return "hello";
    }

    @org.junit.Test
    public void test3() {
        String a = "hello2";
        final String b = getHello(); // 即便是final类型，编译阶段也确定不了值
        String c = b + 2;
        System.out.println((a == c)); // false
    }

}
