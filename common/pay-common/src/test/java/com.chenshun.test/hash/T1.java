package com.chenshun.test.hash;

/**
 * User: chenshun131 <p />
 * Time: 18/3/20 23:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    public static void main(String[] args) {
        String str = "werewrwer24342";
        int hash = str.hashCode() & 3;
        System.out.println(hash);

        System.out.println("Integer.hashCode 1000 => " + new Integer(1000).hashCode());
        System.out.println("Byte.hashCode 1 => " + new Byte("1").hashCode());
    }

}
