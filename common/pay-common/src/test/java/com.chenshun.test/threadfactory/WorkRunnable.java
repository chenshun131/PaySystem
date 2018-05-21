package com.chenshun.test.threadfactory;

/**
 * User: mew <p />
 * Time: 18/5/15 10:09  <p />
 * Version: V1.0  <p />
 * Description: 定义一个测试目标 <p />
 */
public class WorkRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("complete a task");
    }

}
