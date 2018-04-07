package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 22:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MethodCall2 {

    public void sayHello(short a) {
        System.out.println("short");
    }

    public void sayHello(int a) {
        System.out.println("int");
    }

    public void sayHello(long a) {
        System.out.println("long");
    }

    public void sayHello(char a) {
        System.out.println("char");
    }

    public void sayHello(Character a) {
        System.out.println("Character");
    }

    public void sayHello(Object a) {
        System.out.println("Object");
    }

    public void sayHello(char... a) {
        System.out.println("char...");
    }

    public static void main(String[] args) {
        new MethodCall2().sayHello('a'); // 当无法直接确定执行哪个方法，会运行数据类型最接近的方法
    }

}
