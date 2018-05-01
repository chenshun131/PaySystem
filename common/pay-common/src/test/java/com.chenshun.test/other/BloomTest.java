package com.chenshun.test.other;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * User: chenshun131 <p />
 * Time: 18/4/29 12:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BloomTest {

    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        long startTime = System.nanoTime(); // 获取开始时间
        // 判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }

}
