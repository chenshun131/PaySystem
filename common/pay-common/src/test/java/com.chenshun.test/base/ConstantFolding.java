package com.chenshun.test.base;

/**
 * User: chenshun131 <p />
 * Time: 18/4/11 22:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ConstantFolding {

    private static final int number1 = 5;

    private static final int number2 = 6;

    private static int number3 = 5;

    private static int number4 = 6;

    public static void main(String[] args) {
        int product1 = number1 * number2; // line A
        int product2 = number3 * number4; // line B
        System.out.println("product1=" + product1 + " product2=" + product2);
    }

}
