package com.chenshun.test.multipthread.test5;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 22:55  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Product {

    private int id;

    private String name;

    public Product(int id, String name) {
        System.out.println("开始生产 " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.id = id;
        this.name = name;
        System.out.println(name + " 生产完毕");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

}
