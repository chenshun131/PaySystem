package com.chenshun.test.design.decorator;

/**
 * User: mew <p />
 * Time: 18/4/4 10:20  <p />
 * Version: V1.0  <p />
 * Description: ConcreteComponent：具体的对象 <p />
 */
public class Car implements ICar {

    @Override
    public void move() {
        System.out.println("汽车移动");
    }

}
