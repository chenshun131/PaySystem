package com.chenshun.test.multipthread;

import java.util.Arrays;
import java.util.List;

/**
 * User: mew <p />
 * Time: 18/4/10 09:54  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo7 {

    public int add(List<Integer> values) {
        values.parallelStream().forEach(System.out::println);
        return values.parallelStream().mapToInt(i -> i * 2).sum();
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
        int res = new Demo7().add(values);
        System.out.println("计算结果为 : " + res);
    }

}
