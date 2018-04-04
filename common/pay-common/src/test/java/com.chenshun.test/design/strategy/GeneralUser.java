package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:25  <p />
 * Version: V1.0  <p />
 * Description: 具体的算法:普通用户，不打折 <p />
 */
public class GeneralUser implements Strategy {

    @Override
    public double getPrice(double price) {
        System.out.println("普通用户，不打折");
        return price;
    }

}
