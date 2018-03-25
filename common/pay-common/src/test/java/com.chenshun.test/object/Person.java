package com.chenshun.test.object;

/**
 * User: chenshun131 <p />
 * Time: 18/3/25 16:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Person {

    private String name = "Jack";

    public Person() {
        System.out.println("construct Person");
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Class clazz;
        try {
            clazz = Class.forName("com.chenshun.test.object.Person");
            Person p = (Person) clazz.newInstance();
            System.out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
