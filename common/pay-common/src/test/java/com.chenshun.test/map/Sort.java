package com.chenshun.test.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: mew <p />
 * Time: 18/4/18 17:01  <p />
 * Version: V1.0  <p />
 * Description: 学生成绩列表 Map<名字,分数>，实现按照分数排序的接口 <p />
 */
public class Sort {

    /**
     * java 方式排序
     *
     * @param map
     * @return
     */
    public static Map<String, Integer> sort(Map<String, Integer> map) {
        Map<Integer, String> sortMap = new TreeMap<>();
        map.forEach((key, val) -> sortMap.put(val, key));
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        sortMap.forEach((val, key) -> resultMap.put(key, val));
        return resultMap;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(9);
        map.put("av", 1);
        map.put("ff", 6);
        map.put("vh", 8);
        map.put("ca", 3);
        map.put("ai", 9);
        map.put("be", 5);
        map.put("bc", 2);
        map.put("bg", 7);
        map.put("dd", 4);

        map.forEach((key, val) -> System.out.println(key + "=" + val));
        System.out.println("---------------------------");
        sort(map).forEach((key, val) -> System.out.println(key + "=" + val));
    }

}
