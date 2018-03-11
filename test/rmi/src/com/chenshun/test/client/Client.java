package com.chenshun.test.client;

import com.chenshun.test.server.UserHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 14:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Client {

    public static void main(String[] args) throws RemoteException {
        try {
            // 获取该远程对象的引用，可以通过 Naming.list(...) 方法列出所有可用的远程对象
            UserHandler hello = (UserHandler) Naming.lookup("rmi://localhost:1099/hello");
            System.out.println("name: " + hello.getUserName(1));
            System.out.println("count: " + hello.getUserCount());
            System.out.println("user: " + hello.getUserByName("lmy86263"));

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            UserHandler handler = (UserHandler) Naming.lookup("user");
            System.out.println("name: " + handler.getUserName(2));
            System.out.println("count: " + handler.getUserCount());
            System.out.println("user: " + handler.getUserByName("lmy86263"));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

}
