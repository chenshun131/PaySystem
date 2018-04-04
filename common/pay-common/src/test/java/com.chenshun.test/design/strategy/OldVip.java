package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:27  <p />
 * Version: V1.0  <p />
 * Description: 具体的算法：老会员：打5折 <p />
 */
public class OldVip implements Strategy {

    @Override
    public double getPrice(double price) {
        System.out.println("老会员：打5折");
        return price * 0.5;
    }

}
