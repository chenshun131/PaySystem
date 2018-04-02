package com.chenshun.test.design.factory.factorymethod;

import com.chenshun.test.design.factory.Car;

/**
 * User: mew <p />
 * Time: 18/3/28 17:25  <p />
 * Version: V1.0  <p />
 * Description: 工厂接口 <p />
 */
public interface CarFactory {

    /**
     * 创建汽车方法
     *
     * @return
     */
    Car createCar();

}
