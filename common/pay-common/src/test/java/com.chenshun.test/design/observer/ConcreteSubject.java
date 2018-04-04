package com.chenshun.test.design.observer;

/**
 * User: mew <p />
 * Time: 18/4/4 13:59  <p />
 * Version: V1.0  <p />
 * Description: 具体的主题对象 <p />
 */
public class ConcreteSubject extends Subject {

    /** 默认状态为0 */
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 当修改了主题对象状态时，通知所有观察者
        this.notifyAllObserver(); // 通知所有观察者
    }

}
