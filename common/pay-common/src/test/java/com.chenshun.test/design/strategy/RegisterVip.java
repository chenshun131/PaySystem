package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:26  <p />
 * Version: V1.0  <p />
 * Description: 具体的算法：普通会员：打8折 <p />
 */
public class RegisterVip implements Strategy {

    @Override
    public double getPrice(double price) {
        System.out.println("普通会员：打8折");
        return price * 0.8;
    }

}
