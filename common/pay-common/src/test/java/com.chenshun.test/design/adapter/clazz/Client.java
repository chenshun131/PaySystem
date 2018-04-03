package com.chenshun.test.design.adapter.clazz;

/**
 * User: mew <p />
 * Time: 18/4/2 13:23  <p />
 * Version: V1.0  <p />
 * Description: 测试对象适配器模式 <p />
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
        Target t = new Adapter();
        c.test1(t);
    }

}
