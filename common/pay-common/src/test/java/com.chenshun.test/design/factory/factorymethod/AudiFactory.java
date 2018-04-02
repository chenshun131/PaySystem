package com.chenshun.test.design.factory.factorymethod;

import com.chenshun.test.design.factory.Audi;
import com.chenshun.test.design.factory.Car;

/**
 * User: mew <p />
 * Time: 18/3/28 17:27  <p />
 * Version: V1.0  <p />
 * Description: 创建奥迪的工厂 <p />
 */
public class AudiFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Audi();
    }

}
