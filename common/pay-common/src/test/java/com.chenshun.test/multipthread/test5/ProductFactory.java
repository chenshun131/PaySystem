package com.chenshun.test.multipthread.test5;

import java.util.Random;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 22:57  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ProductFactory {

    public Future createProduct(String name) {
        Future f = new Future(); // 创建一个订单
        System.out.println("下单成功，你可以去上班了》。。");
        // 生产产品
        new Thread(() -> {
            Product p = new Product(new Random().nextInt(), name);
            f.setProduct(p);
        }).start();
        return f;
    }

}
