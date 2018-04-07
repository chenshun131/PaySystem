package com.chenshun.test.jvm;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * User: chenshun131 <p />
 * Time: 18/4/5 15:49  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test05 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();

        new Thread(() -> {
            while (true) {
            }
        }, "While True").start();

        new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sleep").start();
        sc.next();
    }

}
