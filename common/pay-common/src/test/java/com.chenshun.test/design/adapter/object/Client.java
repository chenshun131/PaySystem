package com.chenshun.test.design.adapter.object;

/**
 * User: mew <p />
 * Time: 18/4/2 13:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Client {

    /**
     * 说话
     *
     * @param t
     */
    public void test1(Target t) {
        t.handleReq();
    }

    public static void main(String[] args) {
        Client c = new Client();
        Adaptee a = new Adaptee();
        Target t = new Adapter(a); // 需要传入适配器
        c.test1(t);
    }

}
