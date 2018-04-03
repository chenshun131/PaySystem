package com.chenshun.test.design.factory.abstractfactory;

/**
 * User: mew <p />
 * Time: 18/3/28 18:02  <p />
 * Version: V1.0  <p />
 * Description: 汽车总工厂，可以创建轮胎，座椅，发动机 <p />
 */
public interface CarFactory {

    /**
     * 创建发动机
     *
     * @return
     */
    Engine createEngine();

    /**
     * 创建座椅
     *
     * @return
     */
    Seat createSeat();

    /**
     * 创建轮胎
     *
     * @return
     */
    Tyre createTyre();

}
