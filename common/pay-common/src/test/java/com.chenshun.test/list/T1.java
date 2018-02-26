package com.chenshun.test.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mew <p />
 * Time: 18/2/23 10:24  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    private static void updateList(List list) {
        list.remove(new Integer(2)); // 此时 new 不会创建新的对象而是从缓存中拿取
        list.remove(new Integer(128));
    }

    @Test
    public void Test1() {
        // Integer 的缓存策略是 -128 到 +127
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(128);
        updateList(list);
        System.out.println(list); // [1, 3] 说明进行的是值比较
    }

}
