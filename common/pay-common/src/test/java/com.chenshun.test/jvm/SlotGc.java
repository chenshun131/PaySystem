package com.chenshun.test.jvm;

/**
 * User: mew <p />
 * Time: 18/4/9 13:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SlotGc {

    public static void main(String[] args) {
        byte[] holder = new byte[32 * 1024 * 1024];
        System.gc();
    }

}
