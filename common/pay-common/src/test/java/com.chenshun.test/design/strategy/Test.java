package com.chenshun.test.design.strategy;

/**
 * User: mew <p />
 * Time: 18/4/4 12:29  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    public static void main(String[] args) {
        double price = 998; // 商品价格
        Strategy generalUser = new GeneralUser(); // 普通用户
        Strategy registerUser = new RegisterUser(); // 注册用户
        Strategy registerVip = new RegisterVip(); // 普通会员
        Strategy oldVip = new OldVip(); // 老会员

        // 根据不同的用户打不同的折扣
        Context c1 = new Context(generalUser);
        c1.printPrice(price);
        Context c2 = new Context(registerUser);
        c2.printPrice(price);
        Context c3 = new Context(registerVip);
        c3.printPrice(price);
        Context c4 = new Context(oldVip);
        c4.printPrice(price);
    }

}
