package com.chenshun.test.multipthread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 23:46  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo14 {

    public static void main(String[] args) {
        ArrayList<String> s = new ArrayList<>();
        Collections.synchronizedList(s);

        HashMap<String, Object> res = new HashMap<>();
        Collections.synchronizedMap(res);
    }

}
