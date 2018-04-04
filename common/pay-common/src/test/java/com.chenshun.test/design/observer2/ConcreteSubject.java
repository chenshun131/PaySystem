package com.chenshun.test.design.observer2;

import java.util.Observable;

/**
 * User: mew <p />
 * Time: 18/4/4 14:17  <p />
 * Version: V1.0  <p />
 * Description: 具体的目标对象 <p />
 */
public class ConcreteSubject extends Observable {

    private int state;

    public void updateState(int s) {
        state = s; // 目标对象发生了改变
        setChanged(); // Observable类中的setChanged()方法，表示目标对象发生了更改
        notifyObservers(state); // 通知所有观察者对象
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
