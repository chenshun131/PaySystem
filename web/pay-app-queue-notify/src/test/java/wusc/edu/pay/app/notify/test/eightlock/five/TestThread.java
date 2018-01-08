package wusc.edu.pay.app.notify.test.eightlock.five;

/**
 * User: mew <p />
 * Time: 18/1/8 11:22  <p />
 * Version: V1.0  <p />
 * Description: 5.修改getOne()为静态同步方法，一个number对象，打印？// two 3s后打印 one <p />
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
        new Thread(() -> TestThread.getOne()).start();
        new Thread(() -> new TestThread().getTwo()).start();
    }

}
