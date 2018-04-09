package com.chenshun.test.constantpool;

/**
 * User: mew <p />
 * Time: 18/4/8 15:17  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    @org.junit.Test
    public void test1() {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2)); // i1=i2   true
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3)); // i1=i2+i3   true
        System.out.println("i1=i4   " + (i1 == i4)); // i1=i4   false
        System.out.println("i4=i5   " + (i4 == i5)); // i4=i5   false
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6)); // i4=i5+i6   true
        System.out.println("40=i5+i6   " + (40 == i5 + i6)); // 40=i5+i6   true
    }

    @org.junit.Test
    public void test2() {
        String str11 = "abcd";
        String str22 = new String("abcd");
        System.out.println(str11 == str22); // false

        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        System.out.println("string" == "str" + "ing"); // true
        System.out.println(str3 == str4); // false

        String str5 = "string";
        System.out.println(str3 == str5); // true
    }

    @org.junit.Test
    public void test3() {
        String s1 = new String("计算机");
        String s2 = s1.intern();
        String s3 = "计算机";
        System.out.println("s1 == s2? " + (s1 == s2)); // s1 == s2? false
        System.out.println("s3 == s2? " + (s3 == s2)); // s3 == s2? true
    }

}
