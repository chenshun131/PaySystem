package com.chenshun.test.other;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/4/28 09:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    public static void main(String[] args) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean b = map != null ? map.get("test") : false;
        System.out.println(b);
    }

}
