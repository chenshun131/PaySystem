package com.chenshun.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/4/3 21:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JConsoleTest {

    public static void main(String[] args) throws InterruptedException {
        List<JConsoleTest> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(100);
            list.add(new JConsoleTest());
        }
    }

}
