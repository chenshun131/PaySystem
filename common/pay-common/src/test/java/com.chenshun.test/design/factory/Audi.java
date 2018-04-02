package com.chenshun.test.design.factory;

/**
 * User: mew <p />
 * Time: 18/3/28 17:12  <p />
 * Version: V1.0  <p />
 * Description: 奥迪 <p />
 */
public class Audi implements Car {

    @Override
    public void run() {
        System.out.println("奥迪在跑");
    }

}
