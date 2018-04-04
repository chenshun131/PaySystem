package com.chenshun.test.design.observer;

/**
 * User: mew <p />
 * Time: 18/4/4 13:57  <p />
 * Version: V1.0  <p />
 * Description: 具体的观察者 <p />
 */
public class ConcreteObserver implements Observer {

    /** state对象需要和subject中的state保持一致 */
    private int state;

    @Override
    public void upadte(Subject subject) {
        // 当目标对象（主题对象）状态发生改变时，观察者也发生改变。
        this.state = ((ConcreteSubject) subject).getState();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
