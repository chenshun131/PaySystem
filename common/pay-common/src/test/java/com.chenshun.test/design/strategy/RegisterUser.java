package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:26  <p />
 * Version: V1.0  <p />
 * Description: 具体的算法:注册用户：打9折 <p />
 */
public class RegisterUser implements Strategy {

    @Override
    public double getPrice(double price) {
        System.out.println("注册用户：打9折");
        return price * 0.9;
    }

}
