package wusc.edu.pay.app.notify.test.eightlock.two;

/**
 * User: mew <p />
 * Time: 18/1/8 10:54  <p />
 * Version: V1.0  <p />
 * Description: 2.新增Thread.sleep(3000) 给getOne() 打印？// 3s 后打印 one two <p />
 */
public class TestThread {

    public synchronized void one() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("One");
    }

    public synchronized void two() {
        System.out.println("Two");
    }

    public static void main(String[] args) {
        // 同一个对象的普通同步方法共享同步锁
        TestThread testThread = new TestThread();
        new Thread(() -> testThread.one()).start();
        new Thread(() -> testThread.two()).start();
    }

}
