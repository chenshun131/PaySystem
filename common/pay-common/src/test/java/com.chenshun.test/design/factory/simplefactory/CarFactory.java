package com.chenshun.test.design.factory.simplefactory;

import com.chenshun.test.design.factory.Audi;
import com.chenshun.test.design.factory.Byd;
import com.chenshun.test.design.factory.Car;

/**
 * User: mew <p />
 * Time: 18/3/28 17:15  <p />
 * Version: V1.0  <p />
 * Description: 简单工厂模式 : 虽然能通过工厂来创建对象，但是违反了开闭原则，一旦增加功能需要在原有基础上修改代码 <p />
 */
public class CarFactory {

    /**
     * 创建汽车
     *
     * @param type
     * @return
     */
    public static Car createCar(String type) {
        // 该方法还会有问题，假如以后有很多汽车都需要工厂来创建，则又得修改源代码，违反了OCP开闭原则
        if ("audi".equals(type)) {
            return new Audi();
        } else if ("byd".equals(type)) {
            return new Byd();
        } else {
            return null;
        }
    }

}
