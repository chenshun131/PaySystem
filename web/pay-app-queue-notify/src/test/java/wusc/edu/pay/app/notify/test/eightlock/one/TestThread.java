package wusc.edu.pay.app.notify.test.eightlock.one;

/**
 * User: mew <p />
 * Time: 18/1/8 10:46  <p />
 * Version: V1.0  <p />
 * Description: 1.两个普通同步方法，两个线程 ，标准打印，打印？// one two <p />
 */
public class TestThread {

    public synchronized void one() {
        System.out.println("One");
    }

    public synchronized void two() {
        System.out.println("Two");
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        new Thread(() -> testThread.one()).start();
        new Thread(() -> testThread.two()).start();
    }

}
