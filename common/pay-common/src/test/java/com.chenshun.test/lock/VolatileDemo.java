package com.chenshun.test.lock;

/**
 * User: chenshun131 <p />
 * Time: 18/4/23 23:42  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class VolatileDemo {

    private volatile int signal;

    public void set(int value) {
        this.signal = value;
    }

    public int get() {
        return signal;
    }

    public static void main(String[] args) {
        VolatileDemo d = new VolatileDemo();
        new Thread(() -> {
            System.out.println("修改状态的线程执行...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            d.set(1);
            System.out.println("状态值修改成功。。。");
        }).start();

        new Thread(() -> {
            // 等待signal为1开始执行，否则不能执行
            while (d.get() != 1) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 当信号为1 的时候，执行代码
            System.out.println("模拟代码的执行...");
        }).start();
    }

}
