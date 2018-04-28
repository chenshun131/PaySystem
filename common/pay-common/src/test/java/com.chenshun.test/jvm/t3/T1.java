package com.chenshun.test.jvm.t3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 18/4/28 10:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    public static void main(String[] args) throws InterruptedException {
        // -Xms200M -Xmx200M -verbose:gc
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            TimeUnit.SECONDS.sleep(1);
            list.add(new byte[1024 * 1024 * 2]);
        }
    }

}
