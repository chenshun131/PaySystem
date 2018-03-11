package com.chenshun.test.server;

import com.chenshun.test.bean.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 13:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface UserHandler extends Remote {

    String getUserName(int id) throws RemoteException;

    int getUserCount() throws RemoteException;

    User getUserByName(String name) throws RemoteException;

}
