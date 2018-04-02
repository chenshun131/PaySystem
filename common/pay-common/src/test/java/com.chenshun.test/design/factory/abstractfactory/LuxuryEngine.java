package com.chenshun.test.design.factory.abstractfactory;

/**
 * User: mew <p />
 * Time: 18/3/28 18:00  <p />
 * Version: V1.0  <p />
 * Description: 好的发动机 <p />
 */
public class LuxuryEngine implements Engine {

    @Override
    public void run() {
        System.out.println("好发动机转的快");
    }

    @Override
    public void start() {
        System.out.println("启动快，自动启停");
    }

}
