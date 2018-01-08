package wusc.edu.pay.app.notify.test.eightlock.four;

/**
 * User: mew <p />
 * Time: 18/1/8 11:20  <p />
 * Version: V1.0  <p />
 * Description: 4.两个普通同步方法，两个number对象，打印？// two 3s后打印 one <p />
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
        new Thread(() -> new TestThread().one()).start();
        new Thread(() -> new TestThread().two()).start();
    }

}
