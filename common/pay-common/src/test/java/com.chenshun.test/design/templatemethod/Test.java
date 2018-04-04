package com.chenshun.test.design.templatemethod;

/**
 * User: mew <p />
 * Time: 18/4/4 11:16  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    @org.junit.Test
    public void test1() {
        // 定义子类方式实现模板方法
        BankTemplateMethod template = new DrawMoney();
        template.process();
    }

    @org.junit.Test
    public void test2() {
        BankTemplateMethod template = new BankTemplateMethod() {
            /**
             * 这里使用匿名内部类实现:只需实现具体业务部分，其他部分则使用模板方法定义好的骨架
             */
            @Override
            public void transact() {
                System.out.println("我要存钱");
            }
        };
        template.process();
    }

}
