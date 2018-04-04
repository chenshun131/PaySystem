package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:25  <p />
 * Version: V1.0  <p />
 * Description: 抽象算法接口：定义了所有支持算法的公共接口 <p />
 */
public interface Strategy {

    /**
     * 算法方法:打印商品的价格（不同的实现打不同的折扣）
     *
     * @param price
     * @return
     */
    double getPrice(double price);

}
