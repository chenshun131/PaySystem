package wusc.edu.pay.app.notify.test;

/**
 * User: mew <p />
 * Time: 18/1/8 08:48  <p />
 * Version: V1.0  <p />
 * Description: 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为A、 B、 C，每个线程将自己的 ID 在屏幕上打印 10 遍，
 * 要求输出的结果必须按顺序显示，如 : ABCABCABC…… 依次递归 <p />
 */
public class TestABCAlternate {

    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                ad.loopA(i);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                ad.loopB(i);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                ad.loopC(i);
                System.out.println("-----------------------------------");
            }
        }, "C").start();
    }

}
