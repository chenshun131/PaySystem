package com.chenshun.test;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 17:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DiamondOperatorTest {

    @Test
    public void testDiamondOperator() {
        diamondOperator();
    }

    public void diamondOperator() {
        // 创建一个继承于HashSet的匿名子类的对象
        Set<String> set = new HashSet<>() {
        };
        set.add("MM");
        set.add("JJ");
        set.add("GG");
        set.add("DD");
        for (String s : set) {
            System.out.println(s);
        }
    }

}

