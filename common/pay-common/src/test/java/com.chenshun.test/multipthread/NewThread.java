package com.chenshun.test.multipthread;

/**
<<<<<<< HEAD
 * User: mew <p />
 * Time: 18/4/10 09:06  <p />
=======
 * User: chenshun131 <p />
 * Time: 18/4/8 20:59  <p />
>>>>>>> commit some test class
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
<<<<<<< HEAD
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
=======
            System.out.println("自定义的线程执行了....");
        }
    }

    public static void main(String[] args) {
        NewThread n = new NewThread();
        // 初始化状态
        Thread thread = new Thread(n); // 创建线程,并指定线程任务
        thread.start(); // 启动线程

        while (true) {
            synchronized (n) {
                System.out.println("主线程执行了...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n.notifyAll();
>>>>>>> commit some test class
            }
        }
    }

}
