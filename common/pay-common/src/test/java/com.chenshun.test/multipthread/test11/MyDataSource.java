package com.chenshun.test.multipthread.test11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: mew <p />
 * Time: 18/4/28 17:05  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MyDataSource {

    private LinkedList<Connection> pool = new LinkedList<>();

    private static final int INIT_CONNECTIONS = 10;

    private static final String DRIVER_CLASS = "";

    private static final String USER = "";

    private static final String PASSWORD = "";

    private static final String URL = "";

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource() {
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // druid
    public Connection getConnect() {
        Connection result = null;
        lock.lock();
        try {
            while (pool.size() <= 0) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void release(Connection conn) {
        if (conn != null) {
            lock.lock();
            try {
                pool.addLast(conn);
                c1.signal();
            } finally {
                lock.unlock();
            }
        }
    }

}