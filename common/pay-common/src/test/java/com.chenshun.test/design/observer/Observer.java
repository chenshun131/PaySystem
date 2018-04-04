package com.chenshun.test.design.observer;

/**
 * User: mew <p />
 * Time: 18/4/4 13:57  <p />
 * Version: V1.0  <p />
 * Description: 抽象观察者:为所有的观察者定义一个接口 <p />
 */
public interface Observer {

    /**
     * 传入主题对象,得到主题对象的通知时更新自己
     *
     * @param subject
     */
    void upadte(Subject subject);

}
