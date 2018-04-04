package com.chenshun.test.design.observer2;

import java.util.Observable;
import java.util.Observer;

/**
 * User: mew <p />
 * Time: 18/4/4 14:22  <p />
 * Version: V1.0  <p />
 * Description: 具体的观察者 <p />
 */
public class ConcreteObserver implements Observer {

    /** 具体观察者中的状态和目标对象的状态保持一致 */
    private int state;

    @Override
    public void update(Observable o, Object arg) {
        state = ((ConcreteSubject) o).getState();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
