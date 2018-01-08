package wusc.edu.pay.app.notify.test.eightlock;

/**
 * User: mew <p />
 * Time: 18/1/8 10:42  <p />
 * Version: V1.0  <p />
 * Description: <br/>
 * 题目：判断打印 "one" or "two" <br/>
 * <p>
 * 1.两个普通同步方法，两个线程 ，标准打印，打印？// one two <br/>
 * 2.新增Thread.sleep(3000) 给getOne() 打印？// 3s 后打印 one two <br/>
 * 3.新增普通方法 getThree 打印？// 先打印three 三秒后打印 one two <br/>
 * 4.两个普通同步方法，两个number对象，打印？// two 3s后打印 one <br/>
 * 5.修改getOne()为静态同步方法，一个number对象，打印？// two 3s后打印 one <br/>
 * 6.修改两个方法均为静态同步方法，一个number对象，打印？// 3s 后打印 one two <br/>
 * 7.修改getOne()为静态同步方法，getTwo()为非静态同步方法 ，两个number，一个调用one，一个调用two //two 3s后打印 one <br/>
 * 8.两个都改为静态同步方法，两个number 一个调用getOne(),一个调用getTwo() //3s 后打印 one two <p />
 */
public class TestThread8Monitor {

    public static void main(String[] args) {

        final Number number = new Number();
        final Number number2 = new Number();
        new Thread(() -> number.getOne()).start();
        new Thread(() -> number2.getTwo()).start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                number.getThree();
            }
        }).start();*/
    }

}

class Number {

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

    public void getThree() {
        System.out.println("three");
    }

}
