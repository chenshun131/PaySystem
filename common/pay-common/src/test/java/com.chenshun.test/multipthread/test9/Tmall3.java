package com.chenshun.test.multipthread.test9;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * User: mew <p />
 * Time: 18/4/28 16:04  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Tmall3 implements Shop {

    public final int MAX_COUNT = 10;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

    public void push() {
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take() {
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void size() {
        while (true) {
            System.out.println("当前队列的长队为：" + queue.size());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
