package com.chenshun.test.design.decorator;

/**
 * User: mew <p />
 * Time: 18/4/4 10:22  <p />
 * Version: V1.0  <p />
 * Description: ConcreteDecorator：就是具体的装饰对象 <p />
 */
public class FlayCar extends SuperCar {

    public FlayCar(ICar car) {
        super(car);
    }

    /**
     * 这里就是新增的功能
     */
    public void flay() {
        System.out.println("---天上飞");
    }

    @Override
    public void move() {
        super.move();
        // 在原有移动的基础上，装饰了一个fly的功能
        flay();
    }

}
