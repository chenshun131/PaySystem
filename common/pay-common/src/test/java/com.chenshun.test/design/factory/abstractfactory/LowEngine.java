package com.chenshun.test.design.factory.abstractfactory;

/**
 * User: mew <p />
 * Time: 18/3/28 18:00  <p />
 * Version: V1.0  <p />
 * Description: 差的发动机 <p />
 */
public class LowEngine implements Engine {

    @Override
    public void run() {
        System.out.println("转的慢");
    }

    @Override
    public void start() {
        System.out.println("启动慢");
    }

}
