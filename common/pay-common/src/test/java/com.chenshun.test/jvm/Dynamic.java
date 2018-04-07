package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 22:50  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Dynamic {

    static class Parent {

        public void sayHello() {
            System.out.println("Parent");
        }
    }

    static class Child1 extends Parent {

        @Override
        public void sayHello() {
            System.out.println("Child1");
        }
    }

    static class Child2 extends Parent {

        @Override
        public void sayHello() {
            System.out.println("Child2");
        }
    }

    public static void main(String[] args) {
        Parent p1 = new Child1();
        Parent p2 = new Child2();

        p1.sayHello(); // 打印 Child1
        p2.sayHello(); // 打印 Child2
    }

}
