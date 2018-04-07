package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 17:49  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GCDemo1 {

    public static void main(String[] args) {
        {
            byte[] buff = new byte[60 * 1024 * 1028];
        }
        int a = 10;
        System.gc();
    }

}
