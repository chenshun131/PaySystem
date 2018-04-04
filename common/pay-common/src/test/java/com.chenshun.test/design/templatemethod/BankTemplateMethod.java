package com.chenshun.test.design.templatemethod;

/**
 * User: mew <p />
 * Time: 18/4/4 11:13  <p />
 * Version: V1.0  <p />
 * Description: 模板抽象方法 <p />
 */
public abstract class BankTemplateMethod {

    /**
     * 模板方法中其他业务逻辑
     * 1.取号排队
     */
    public void takeNumber() {
        System.out.println("取号排队");
    }

    /**
     * 2.办理具体的业务：这里留给子类来实现
     */
    public abstract void transact();

    /**
     * 3.给客服评分
     */
    public void evaluate() {
        System.out.println("反馈评分");
    }

    /**
     * 模板方法
     */
    public final void process() {
        this.takeNumber();
        // 这里则是具体的模板方法函数
        this.transact();
        this.evaluate();
    }

}
