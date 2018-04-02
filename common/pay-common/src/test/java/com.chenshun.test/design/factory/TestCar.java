package com.chenshun.test.design.factory;

import com.chenshun.test.design.factory.factorymethod.AudiFactory;
import com.chenshun.test.design.factory.factorymethod.BydFactory;
import com.chenshun.test.design.factory.simplefactory.CarFactory;
import org.junit.Test;

/**
 * User: mew <p />
 * Time: 18/3/28 17:13  <p />
 * Version: V1.0  <p />
 * Description: 汽车测试类 <p />
 */
public class TestCar {

    public static void main(String[] args) {
        // 未使用工厂模式的时候，调用者会依赖很多类，违反了开闭原则
        Car c1 = new Byd();
        Car c2 = new Audi();
        c1.run();
        c2.run();
    }

    @Test
    public void testSimpleFactoryCar() {
        // 创建汽车
        Car audi = CarFactory.createCar("audi"); // 创建者
        Car byd = CarFactory.createCar("byd");
        assert audi != null;
        audi.run();
        assert byd != null;
        byd.run();
    }

    @Test
    public void testFactoryMethod() {
        // 工厂方法模式好处在于，以后如果再增加一辆车，只需再实现CarFactory接口即可，避免 OCP开闭原则
        // 不用在原来的代码上修改，只需新增类即可
        // 例如 : 增加一辆奔驰，增加一个奔驰工厂 BenzFactory即可，更好扩展
        Car audi = new AudiFactory().createCar();
        Car byd = new BydFactory().createCar();
        audi.run();
        byd.run();
    }

}
