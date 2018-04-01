package com.chenshun.test.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/4/1 13:48  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    private String a;

    private String b;

    public Test() {
    }

    public Test(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

//    @Override
//    public String toString() {
//        return "Test{" + "a='" + a + "', b='" + b + "'}";
//    }

    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.add(new Test("a1", "b1"));
        list.add(new Test("a2", "b2"));
        list.add(new Test("a3", "b3"));

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.deepToString(list.toArray()));
    }

}
