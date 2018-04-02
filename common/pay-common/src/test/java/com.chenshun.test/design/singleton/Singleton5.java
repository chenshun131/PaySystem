package com.chenshun.test.design.singleton;

/**
 * User: mew <p />
 * Time: 18/3/28 16:57  <p />
 * Version: V1.0  <p />
 * Description: 枚举实现单例模式 (枚举本身就是单例) <p />
 */
public enum Singleton5 {

    /** 定义一个枚举元素，它就是一个单例的实例 */
    INSTANCE;

    /**
     * 对枚举的一些操作
     */
    public void singletonOperation() {
    }

}