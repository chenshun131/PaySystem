package com.chenshun.test.multipthread;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo12 {

    private int a;

    private volatile boolean flag;

    public void writer() {
        a = 1; // 1
        flag = true; // 2   当写一个volatile变量时，Java内存模型会把该线程对应的本地内存中的共享变量值刷新到主内存中
    }

    public void reader() {
        if (flag) { // 3 当读一个volatile变量时，Java内存模型会把当前线程对应的本地内存中的共享变量置为无效，然后从主内存中读取共享变量。
            int b = a + 1; // 4
            System.out.println(b); // 5
        }
    }

}
