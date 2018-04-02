package com.chenshun.test.design.factory;

/**
 * User: mew <p />
 * Time: 18/3/28 17:13  <p />
 * Version: V1.0  <p />
 * Description: 比亚迪 <p />
 */
public class Byd implements Car {

    @Override
    public void run() {
        System.out.println("比亚迪在跑");
    }

}
