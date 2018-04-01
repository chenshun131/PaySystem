package com.chenshun.test.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: chenshun131 <p />
 * Time: 18/4/1 12:19  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public final class Test {

    private final int a = 100;

    @org.junit.Test
    public void test() {
        // 要验证的字符串
        String str = "service@xsoftlab.net";
        // 邮箱验证规则
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    @org.junit.Test
    public void test1() {
        System.out.println(3 * 0.1 == 0.3); // false
    }

}
