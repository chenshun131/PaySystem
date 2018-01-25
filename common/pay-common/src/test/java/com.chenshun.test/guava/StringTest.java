package com.chenshun.test.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/1/25 10:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class StringTest {

    @Test
    public void test1() {
        // 使用com.google.common.base.Strings类的isNullOrEmpty(input)方法判断字符串是否为空

        // Strings.isNullOrEmpty(input) demo
        String input = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty ? "is" : "is not") + " null or empty.");
    }

    @Test
    public void test2() {
        // 获得两个字符串相同的前缀或者后缀

        // Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a, b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        // Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c, d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }

    @Test
    public void test3() {
        // Strings的 padStart和 padEnd方法来补全字符串

        int minLength = 4;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);

        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);
    }

    @Test
    public void test4() {
        // 使用Splitter类来拆分字符串
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }

    @Test
    public void test5() {
        // Splitter还有更强大的功能，做二次拆分，这里二次拆分的意思是拆分两次，例如我们可以将a=b;c=d这样的字符串拆分成一个Map<String,String>
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }

    @Test
    public void test6() {
        // 有拆分字符串必然就有合并字符串，guava为我们提供了Joiner类来做字符串的合并
        String joinResult = Joiner.on(" ").join(new String[]{"hello", "world"});
        System.out.println(joinResult);
    }

    @Test
    public void test7() {
        // Splitter方法可以对字符串做二次的拆分，对应的Joiner也可以逆向操作，将Map<String,String>做合并
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }

    @Test
    public void test8() {
        // 在开发中经常会需要比较两个对象是否相等，这时候我们需要考虑比较的两个对象是否为null，然后再调用equals方法来比较是否相等，
        // google guava库的com.google.common.base.Objects类提供了一个静态方法equals可以避免
        Object a = null;
        Object b = new Object();
        Assert.assertTrue(Objects.equal(a, b));
    }

}
