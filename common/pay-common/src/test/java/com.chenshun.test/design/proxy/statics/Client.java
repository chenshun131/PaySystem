package com.chenshun.test.design.proxy.statics;

/**
 * User: mew <p />
 * Time: 18/4/4 09:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Client {

    public static void main(String[] args) {
        Star real = new RealStar();
        Star proxy = new ProxyStar(real);
        proxy.confer();
        proxy.signContract();
        proxy.bookTicket();
        proxy.sing(); // 真实对象的操作（明星唱歌）
        proxy.collectMoney();
    }

}
