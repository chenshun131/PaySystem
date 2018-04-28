package com.chenshun.test.multipthread.test6;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:24  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo2 {

    private int a;

    private boolean flag;

    public void writer() {
        // 这两个数据之间没有数据依赖性，因此处理器会对这两行代码进行指令重排序
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            int b = a + 1;
            System.out.println(b);
        }
    }

}
