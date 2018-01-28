package com.chenshun.test.exception;

import org.junit.Test;

/**
 * User: mew <p />
 * Time: 18/1/25 10:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestException {

    @Test
    public void t1() {
        int i = 0;
        String greetings[] = {" Hello world !", " Hello World !! ", " HELLO WORLD !!!"};
        while (i < 4) {
            try {
                // 特别注意循环控制变量i的设计，避免造成无限循环
                System.out.println(greetings[i++]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("数组下标越界异常");
            } finally {
                System.out.println("--------------------------");
            }
        }
    }

    public static void main(String[] args) {
        int[] intArray = new int[3];
        try {
            for (int i = 0; i <= intArray.length; i++) {
                intArray[i] = i;
                System.out.println("intArray[" + i + "] = " + intArray[i]);
                System.out.println("intArray[" + i + "]模 " + (i - 2) + "的值:  " + intArray[i] % (i - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("intArray数组下标越界异常。");
        } catch (ArithmeticException e) {
            System.out.println("除数为0异常。");
        }
        System.out.println("程序正常结束。");
    }

}
