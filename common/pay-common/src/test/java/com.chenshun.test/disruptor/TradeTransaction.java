package com.chenshun.test.disruptor;

/**
 * User: mew <p />
 * Time: 18/4/16 13:10  <p />
 * Version: V1.0  <p />
 * Description: 创建一个实体类用于封装对象 <p />
 */
public class TradeTransaction {

    /** 交易ID */
    private String id;

    /** 交易金额 */
    private double price;

    public TradeTransaction() {
    }

    public TradeTransaction(String id, double price) {
        super();
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
