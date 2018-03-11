package com.chenshun.test.server.impl;

import com.chenshun.test.bean.User;
import com.chenshun.test.server.UserHandler;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {

    private static final long serialVersionUID = -6916371403662229854L;

    /**
     * 该构造期必须存在，因为集继承了UnicastRemoteObject类，其构造器要抛出RemoteException
     *
     * @throws RemoteException
     */
    public UserHandlerImpl() throws RemoteException {
        super();
    }

    @Override
    public String getUserName(int id) throws RemoteException {
        if (id == 1) {
            return "hello chenshun131";
        } else if (id == 2) {
            return "user chenshun131";
        }
        return "unknow";
    }

    @Override
    public int getUserCount() throws RemoteException {
        return 1;
    }

    @Override
    public User getUserByName(String name) throws RemoteException {
        return new User("chenshun131", 1);
    }

    public static void main(String[] args) throws RemoteException {
        // 启动 rmiregistry 服务
        LocateRegistry.createRegistry(1099);

        try {
            UserHandler userHandler = new UserHandlerImpl();
            // Naming 类提供在对象注册表中存储和获得远程对远程对象引用的方法，每个方法都可将某个名称作为其一个参数，
            // 该名称是使用以下形式的 URL格式(没有 scheme 组件) 的 java.lang.String :
            // host:port/name
            // host : 注册表所在的主机（远程或本地)，省略则默认为本地主机
            // port : 是注册表接受调用的端口号，省略则默认为1099，RMI注册表registry使用的著名端口
            // name : 是未经注册表解释的简单字符串
            // Naming.bind("//host:port/name", h);
            Naming.bind("rmi://localhost:1099/hello", userHandler); // 通过一个名称映射到该远程对象的引用，客户端通过该名称获取该远程对象的引用

            Naming.rebind("user", userHandler);
            System.out.println(" rmi server is ready ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
