package com.chenshun.test.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/3/13 20:32  <p />
 * Version: V1.0  <p />
 * Description:
 * 新形式的遍历虽然好用，但是他丢掉了索引信息。当你需要访问数组或者集合的下标，你将不能使用foreach <br/>
 * 从JDK的第五个版本开始加入了这个foreach功能，但是java语言对于它的实现做了隐藏，他是隐藏在语言内部的 <br/>
 * <br/>
 * foreach循环的几个特性 <br/>
 * 1.foreach遍历不能对元素进行赋值操作 <br/>
 * 2.同时只能遍历一个 <br/>
 * 3.遍历的时候，只有当前被遍历的元素可见，其他不可见 <br/>
 * 4.只能正向遍历，不能反向 <br/>
 * 5.foreach对ArrayList的遍历是因为其实现了Iterable接口 <br/>
 */
public class ForeachTest {

    private static int[] array = {1, 2, 3};

    private static int[][] arrayTwo = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    private static List<String> list = new ArrayList<>();

    static {
        list.add("array - 1");
        list.add("array - 2");
        list.add("array - 3");
    }

    public static void main(String[] args) {
        oldWrite();
        newWrite();
        foreachTwo();
        forList();
        iteratorList();
        foreachList();
    }

    /**
     * 旧形式的遍历
     */
    private static void oldWrite() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 新形式的遍历
     *
     * @since JDK5.0
     */
    private static void newWrite() {
        for (int i : array) {
            // foreach实现原理一 : 实际上本方法去遍历数组的时候使用的是 for一样的方式去循环遍历数组
            System.out.println(i);
        }
    }

    /**
     * 新形式对于二维的遍历
     *
     * @since JDK5.0
     */
    private static void foreachTwo() {
        for (int[] i : arrayTwo) {
            for (int j : i) {
                System.out.println(j);
            }
        }
    }

    /**
     * 旧方式遍历集合
     */
    private static void forList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));

        }
    }

    /**
     * 使用迭代器遍历集合
     */
    private static void iteratorList() {
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 使用新形式遍历集合
     *
     * @since JDK5.0
     */
    private static void foreachList() {
        for (String string : list) {
            // foreach实现原理二 : 实际上本方法遍历容器使用的方式是通过迭代器来进行的
            System.out.println(string);
        }
    }

}
