package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 21:46  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MethodCall {

    static class Parent {

    }

    static class Child1 extends Parent {

    }

    static class Child2 extends Parent {

    }

    public void sayHello(Child1 c) {
        System.out.println("c1 is call");
    }

    public void sayHello(Child2 c) {
        System.out.println("c2 is call");
    }

    public void sayHello(Parent p) {
        System.out.println("p is call");
    }

    public static void main(String[] args) {
        Parent p1 = new Child1();
        Parent p2 = new Child2();

        MethodCall mc = new MethodCall();
        mc.sayHello(p1); // 打印 p is call
        mc.sayHello(p2); // 打印 p is call

        // 实际类型发生改变
        Parent p = new Child1();
        p = new Child2();

        // 静态类型发生变化
        mc.sayHello((Child2) p); // 打印 c2 is call
    }

}
