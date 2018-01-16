package com.chenshun.test.forkjoin;

/**
 * User: mew <p />
 * Time: 18/1/15 08:52  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Product {

    private String name;

    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
