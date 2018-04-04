package com.chenshun.test.design.templatemethod;

/**
 * User: mew <p />
 * Time: 18/4/4 11:15  <p />
 * Version: V1.0  <p />
 * Description: 子类实现模板方法：取款 <p />
 */
public class DrawMoney extends BankTemplateMethod {

    @Override
    public void transact() {
        System.out.println("我要取款");
    }

}
