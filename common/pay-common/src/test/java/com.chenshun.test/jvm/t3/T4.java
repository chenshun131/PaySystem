package com.chenshun.test.jvm.t3;

/**
 * User: mew <p />
 * Time: 18/4/28 11:45  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T4 {

    public static void main(String[] args) {
        // 断言1结果为true，则继续往下执行
        assert true;
        System.out.println("断言1没有问题，Go！");
        System.out.println("\n-----------------\n");
        // 断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");

        // -da
        // 断言1没有问题，Go！
        //
        // -----------------
        //
        // 断言2没有问题，Go！

        // -ea
        // 断言1没有问题，Go！
        // Exception in thread "main" java.lang.AssertionError: 断言失败，此表达式的信息将会在抛出异常的时候输出！
        //
        // -----------------
        //
        //   at com.chenshun.test.jvm.t3.T4.main(T4.java:19)
    }

}
