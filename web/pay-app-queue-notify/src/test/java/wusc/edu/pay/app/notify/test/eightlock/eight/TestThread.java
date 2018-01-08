package wusc.edu.pay.app.notify.test.eightlock.eight;

/**
 * User: mew <p />
 * Time: 18/1/8 11:33  <p />
 * Version: V1.0  <p />
 * Description: 8.两个都改为静态同步方法，两个number 一个调用getOne(),一个调用getTwo() //3s 后打印 one two <p />
 */
public class TestThread {

    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }

    public static void main(String[] args) {
        final TestThread one = new TestThread();
        final TestThread two = new TestThread();
        new Thread(() -> one.getOne()).start();
        new Thread(() -> two.getTwo()).start();
    }

}
