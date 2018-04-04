package com.chenshun.test.design.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * User: mew <p />
 * Time: 18/4/4 09:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Client {

    public static void main(String[] args) {
        // 真实角色
        Star realStar = new RealStar();
        // 处理器
        StarHandler handler = new StarHandler(realStar);
        // 代理类
        Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, handler);
        // 调用代理类的唱歌方法：其实调用的是真实角色的唱歌方法
        proxy.sing();
    }

}
