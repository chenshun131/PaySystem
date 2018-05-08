package com.chenshun.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * User: chenshun131 <p />
 * Time: 18/1/24 21:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class data13 {

    @Test
    public void T1() {
        int a = 129;
        int b = 128;
        System.out.println("a 和b 与的结果是：" + (a & b));
    }

    @Test
    public void T2() {
        int a = 129;
        int b = 128;
        System.out.println("a 和b 或的结果是：" + (a | b));
    }

    @Test
    public void t3() {
        int a = 2;
        System.out.println(String.format("a(%s) 非的结果是：%s(%s)", Integer.toBinaryString(a), ~a, Integer.toBinaryString(~a)));
    }

    @Test
    public void t4() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入被除数：");
        try {
            int num1 = input.nextInt();
            System.out.println("请输入除数：");
            int num2 = input.nextInt();
            System.out.println(String.format("%d / %d = %d", num1, num2, num1 / num2));
        } catch (Exception e) {
            System.err.println("出现错误：被除数和除数必须是整数，除数不能为零。");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void t5() {
        String[] strs = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (String str : strs) {
            System.out.println(str);
        }
    }

    @Test
    public void t6() {
        String str = new String("abc");
        System.out.println(str.hashCode());
        System.out.println("----------");
        System.out.println("gdejicbegh".hashCode());
        System.out.println("hgebcijedg".hashCode());
    }

    @Test
    public void t7() {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        list.stream().skip(5).limit(3).forEach(System.out::println);
    }

    @Test
    public void t8() {
        String jsonString = "{\"name\":\"aaa\",\"age\":1}";
        if (jsonString != null) {

        }
    }

}
