package com.chenshun.test.multipthread.test5;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 22:57  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    public static void main(String[] args) {
        ProductFactory pf = new ProductFactory();
        // 下单，交钱
        Future f = pf.createProduct("蛋糕");
        System.out.println("我去上班了，下了班我来取蛋糕...");

        // 拿着订单获取产品
        System.out.println("我拿着蛋糕回家." + f.get());
    }

}
