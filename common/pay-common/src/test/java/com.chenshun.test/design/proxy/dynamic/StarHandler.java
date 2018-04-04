package com.chenshun.test.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * User: mew <p />
 * Time: 18/4/4 09:22  <p />
 * Version: V1.0  <p />
 * Description: 处理器 <p />
 */
public class StarHandler implements InvocationHandler {

    /** 真实角色 */
    private Star realStar;

    /**
     * 通过构造器来初始化真实角色
     *
     * @param realStar
     */
    public StarHandler(Star realStar) {
        super();
        this.realStar = realStar;
    }

    /**
     * 所有的流程控制都在invoke方法中
     * proxy：代理类
     * method：正在调用的方法
     * args：方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        System.out.println("真实角色调用之前的处理......");
        if (method.getName().equals("sing")) {
            // 激活调用的方法
            object = method.invoke(realStar, args);
        }
        System.out.println("真实角色调用之后的处理......");
        return object;
    }

}
