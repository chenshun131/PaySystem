package com.chenshun.test.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * User: chenshun131 <p />
 * Time: 18/2/14 17:35  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test1 {

    @Test
    public void test1() {
        // test对象未消亡之前，object和str都是强引用
        Object object = new Object();
        String str = "hello";
    }

    @Test
    public void test2() {
        // 软引用只有在内存不足时才会回收
        SoftReference<String> sr = new SoftReference<>("hello");
        System.out.println(sr.get());
        System.gc();
        System.out.println(sr.get());
    }

    @Test
    public void test3() {
        // 弱引用只要被 GC，就会回收
        WeakReference<String> sr = new WeakReference<>("hello");
        System.out.println(sr.get());
        System.gc(); // 通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }

    @Test
    public void test4() {
        // 虚引用
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> pr = new PhantomReference<>("hello", queue);
        System.out.println(pr.get());
    }

}
