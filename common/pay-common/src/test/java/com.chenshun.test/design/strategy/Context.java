package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:28  <p />
 * Version: V1.0  <p />
 * Description: 上下文：用来维护不同对象的不同折扣 <p />
 */
public class Context {

    /** 持有算法族的引用 */
    private Strategy strategy;

    public Context(Strategy strategy) {
        super();
        this.strategy = strategy;
    }

    /**
     * 打印价钱
     */
    public void printPrice(double price) {
        System.out.println("应付金额：" + Math.round(strategy.getPrice(price)));
    }

}
