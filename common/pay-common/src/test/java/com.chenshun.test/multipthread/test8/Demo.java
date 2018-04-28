package com.chenshun.test.multipthread.test8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: mew <p />
 * Time: 18/4/28 15:43  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo {

    public static void main(String[] args) {
        ArrayList<String> s = new ArrayList<>();
        Collections.synchronizedList(s);

        HashMap<String, Object> res = new HashMap<>();
        Collections.synchronizedMap(res);

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    }

}
