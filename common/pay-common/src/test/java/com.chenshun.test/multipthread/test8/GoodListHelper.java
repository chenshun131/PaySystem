package com.chenshun.test.multipthread.test8;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: mew <p />
 * Time: 18/4/28 14:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@ThreadSafe
public class GoodListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }

}
