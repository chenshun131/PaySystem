package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 15:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Child extends Parent {

    public static int B = A; // B 的值是 2

    public static void main(String[] args) {
        System.out.print(Child.B);
    }

}
