package com.chenshun.test.design.adapter.object;

/**
 * User: mew <p />
 * Time: 18/4/2 13:35  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Adapter implements Target {

    /** 这里需要和被适配对象关联起来: 1.继承 2.组合(推荐) */
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        super();
        this.adaptee = adaptee;
    }

    @Override
    public void handleReq() {
        adaptee.request();
    }

}
