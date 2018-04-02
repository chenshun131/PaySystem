package com.chenshun.test.design.factory.factorymethod;

import com.chenshun.test.design.factory.Byd;
import com.chenshun.test.design.factory.Car;

/**
 * User: mew <p />
 * Time: 18/3/28 17:26  <p />
 * Version: V1.0  <p />
 * Description: 创建比亚迪汽车的工厂 <p />
 */
public class BydFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Byd();
    }

}
