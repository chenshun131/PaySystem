package com.chenshun.test.map;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * User: mew <p />
 * Time: 18/3/28 10:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MapCompare {

    private static final int SIZE = 10;

    private Map<String, Integer> mapData = new HashMap<>(SIZE);

    @Before
    public void initData() {
        Random random = new Random();
        String[] strs = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t"};
        for (int i = 0; i < SIZE; i++) {
            mapData.put(strs[random.nextInt(SIZE)], random.nextInt(100) + 1);
        }
    }

    private void addEntity(Map map) {
        mapData.forEach(map::put);
    }

    @Test
    public void testMap() {
        HashMap hashMap = new HashMap(SIZE);
        addEntity(hashMap);

        TreeMap treeMap = new TreeMap();
        addEntity(treeMap);

        LinkedHashMap linkedHashMap = new LinkedHashMap(SIZE);
        addEntity(linkedHashMap);

        System.out.println("----------------- HashMap -----------------");
        hashMap.forEach((key, val) -> System.out.print("  " + key + "=" + val));
        System.out.println("\n----------------- TreeMap -----------------");
        treeMap.forEach((key, val) -> System.out.print("  " + key + "=" + val));
        System.out.println("\n-------------- LinkedHashMap --------------");
        linkedHashMap.forEach((key, val) -> System.out.print("  " + key + "=" + val));
    }

}
