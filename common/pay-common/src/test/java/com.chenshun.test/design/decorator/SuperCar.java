package com.chenshun.test.design.decorator;

/**
 * User: mew <p />
 * Time: 18/4/4 10:21  <p />
 * Version: V1.0  <p />
 * Description: Decorator：装饰抽象类 <p />
 */
public class SuperCar implements ICar {

    /** 持有一个真实对象的引用 */
    protected ICar car;

    /**
     * 构造的时候传参
     *
     * @param car
     */
    public SuperCar(ICar car) {
        super();
        this.car = car;
    }

    @Override
    public void move() {
        // 这里调用真实对象的移动方法
        car.move();
    }

}