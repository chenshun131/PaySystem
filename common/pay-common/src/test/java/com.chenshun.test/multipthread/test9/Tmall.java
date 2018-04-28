package com.chenshun.test.multipthread.test9;

/**
 * User: mew <p />
 * Time: 18/4/28 16:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Tmall implements Shop {

    private int count;

    public final int MAX_COUNT = 10;

    public synchronized void push() {
        while (count >= MAX_COUNT) {
            try {
                System.out.println(Thread.currentThread().getName() + " 库存数量达到上限，生产者停止生产。");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + " 生产者生产，当前库存为：" + count);
        notifyAll();
    }

    public synchronized void take() {
        while (count <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 库存数量为零，消费者等待。");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " 消费者消费，当前库存为：" + count);
        notifyAll();
    }

    @Override
    public void size() {
    }

}
