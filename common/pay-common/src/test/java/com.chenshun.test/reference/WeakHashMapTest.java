package com.chenshun.test.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * User: chenshun131 <p />
 * Time: 18/2/14 18:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class WeakHashMapTest {

    public static void main(String[] args) {
        String test = "test";
        String tmp = "tmp";
        WeakHashMap weakmap = new WeakHashMap();
        Map map = new HashMap();
        map.put(test, "test");
        map.put(tmp, "tmp");

        weakmap.put(test, "test");
        weakmap.put(tmp, "tmp");

        map.remove(test);

        // 清除强引用
        test = null;
        tmp = null;

        System.gc();

        // map:tmp:tmp
        for (Object o : map.entrySet()) {
            Map.Entry en = (Map.Entry) o;
            System.out.println("map:" + en.getKey() + ":" + en.getValue());
        }

        // weakmap:tmp:tmp
        // weakmap:test:test
        for (Object o : weakmap.entrySet()) {
            Map.Entry en = (Map.Entry) o;
            System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());
        }
    }

}
