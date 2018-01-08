package wusc.edu.pay.app.notify.test.eightlock.three;

/**
 * User: mew <p />
 * Time: 18/1/8 11:01  <p />
 * Version: V1.0  <p />
 * Description: 3.新增普通方法 getThree 打印？// 先打印three 三秒后打印 one two <p />
 */
public class TestThread {

    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("three");
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        new Thread(() -> testThread.getOne()).start();
        new Thread(() -> testThread.getTwo()).start();
        new Thread(() -> testThread.getThree()).start();
    }

}
