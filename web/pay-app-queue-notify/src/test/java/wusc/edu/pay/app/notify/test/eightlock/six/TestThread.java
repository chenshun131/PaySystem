package wusc.edu.pay.app.notify.test.eightlock.six;

/**
 * User: mew <p />
 * Time: 18/1/8 11:26  <p />
 * Version: V1.0  <p />
 * Description: 6.修改两个方法均为静态同步方法，一个number对象，打印？// 3s 后打印 one two <p />
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
        new Thread(() -> TestThread.getOne()).start();
        new Thread(() -> TestThread.getTwo()).start();
    }

}
