package com.chenshun.test.testoverride;

/**
 * User: mew <p />
 * Time: 18/4/12 09:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Son extends Father {

    public String name = "Son'name";

//    @Override
//    public void method() {
//        super.method();
//    }

    public static void main(String[] args) {
        Father father = new Son();
        father.method();

        System.out.println("name : " + father.name);
    }

}
