package com.chenshun.test.multipthread.test9;

/**
 * User: mew <p />
 * Time: 18/4/28 16:05  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Main {

    public static void main(String[] args) {
        Shop tmall = new Tmall3();

        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

        new Thread(() -> tmall.size()).start();
    }

}
