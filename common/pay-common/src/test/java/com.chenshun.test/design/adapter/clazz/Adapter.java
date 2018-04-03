package com.chenshun.test.design.adapter.clazz;

/**
 * User: mew <p />
 * Time: 18/4/2 13:22  <p />
 * Version: V1.0  <p />
 * Description: 具体的适配器的实现 <p />
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void handleReq() {
        super.request();
    }

}
