package com.chenshun.test.lock;

import java.util.List;
import java.util.Vector;

/**
 * User: mew <p />
 * Time: 18/4/12 10:05  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Biased {

    public static void main(String[] args) {
        // 288ms : -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
        // 490ms : -XX:-UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m

        // Vector 是线程安全的，add 方法使用 synchronized
        List<Integer> numberList = new Vector<>();

        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}
