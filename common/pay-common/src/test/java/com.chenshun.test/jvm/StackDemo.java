package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/2/11 23:09  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class StackDemo {

    public static void sayHello() {
        sayHello();
    }

    public static void main(String[] args) {
        sayHello();
    }

}
