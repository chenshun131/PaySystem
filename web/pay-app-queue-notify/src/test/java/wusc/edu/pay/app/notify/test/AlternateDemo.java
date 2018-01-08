package wusc.edu.pay.app.notify.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: mew <p />
 * Time: 18/1/8 10:10  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class AlternateDemo {

    private int number = 1; // 当前正在执行线程的标记

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private Condition condition3 = lock.newCondition();

    /**
     * @param totalLoop
     *         循环第几轮
     */
    public void loopA(int totalLoop) {
        lock.lock();
        try {
            // 1. 判断
            if (number != 1) {
                condition1.await();
            }
            // 2. 打印
            System.out.println(Thread.currentThread().getName() + "\t" + totalLoop);
            // 3. 唤醒
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop) {
        lock.lock();
        try {
            // 1. 判断
            if (number != 2) {
                condition2.await();
            }
            // 2. 打印
            System.out.println(Thread.currentThread().getName() + "\t" + totalLoop);
            // 3. 唤醒
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLoop) {
        lock.lock();
        try {
            // 1. 判断
            if (number != 3) {
                condition3.await();
            }
            // 2. 打印
            System.out.println(Thread.currentThread().getName() + "\t" + totalLoop);
            // 3. 唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
