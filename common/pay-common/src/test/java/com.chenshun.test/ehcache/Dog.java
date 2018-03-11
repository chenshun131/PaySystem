package com.chenshun.test.ehcache;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 16:28  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Dog {

    private long id;

    private String name;

    private int age;

    public Dog(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }

}
