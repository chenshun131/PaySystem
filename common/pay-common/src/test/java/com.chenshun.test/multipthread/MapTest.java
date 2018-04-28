package com.chenshun.test.multipthread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: mew <p />
 * Time: 18/4/28 15:50  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MapTest {

    private static Map<String, Object> map1 = new HashMap<>();

    private static Map<String, Object> map2 = new Hashtable<>();

    private static Map<String, Object> map3 = new ConcurrentHashMap<>();

    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<>());

    private static Map<String, Object> map = map4;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (map.size() > 0) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
                    }
                    map.clear();
                }
                try {
                    Thread.sleep((new Random().nextInt(10) + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                map.put("key" + i, "value" + i);
                try {
                    Thread.sleep((new Random().nextInt(10) + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
