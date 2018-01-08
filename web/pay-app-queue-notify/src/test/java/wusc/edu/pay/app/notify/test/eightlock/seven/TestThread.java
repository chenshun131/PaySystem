package wusc.edu.pay.app.notify.test.eightlock.seven;

/**
 * User: mew <p />
 * Time: 18/1/8 11:29  <p />
 * Version: V1.0  <p />
 * Description: 7.修改getOne()为静态同步方法，getTwo()为非静态同步方法 ，两个number，一个调用one，一个调用two //two 3s后打印 one <p />
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

    public synchronized void getTwo() {
        System.out.println("two");
    }

    public static void main(String[] args) {
        TestThread one = new TestThread();
        TestThread two = new TestThread();
        new Thread(() -> one.getOne()).start();
        new Thread(() -> two.getTwo()).start();
    }

}
