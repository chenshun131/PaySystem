package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/10 09:06  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class NewThread implements Runnable {

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程执行了......");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NewThread newThread = new NewThread();
        // 初始化线程状态
        Thread thread = new Thread(newThread); // 创建线程，并指定线程任务
        thread.start(); // 启动线程
        while (true) {
            synchronized (newThread) {
                Thread.sleep(100);
                newThread.notifyAll();
            }
        }
    }

}
