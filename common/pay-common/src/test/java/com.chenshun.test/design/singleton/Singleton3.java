package com.chenshun.test.design.singleton;

/**
 * User: mew <p />
 * Time: 18/3/28 16:49  <p />
 * Version: V1.0  <p />
 * Description: 双重检索单例模式 : 将锁加在判断实例为空的地方，不加在方法上 <p />
 */
public class Singleton3 {

    /**
     * 1、提供未实例化的静态实例
     */
    private static volatile Singleton3 instance = null;

    /**
     * 2、私有化构造器
     */
    private Singleton3() {
    }

    /**
     * 3、对外提供获取实例的方法
     * 但是同步的时候将锁放到第一次获取实例的时候，这样的好处就是只有第一次会同步。效率高
     *
     * @return
     */
    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                instance = new Singleton3();
            }
        }
        return instance;
    }

}
