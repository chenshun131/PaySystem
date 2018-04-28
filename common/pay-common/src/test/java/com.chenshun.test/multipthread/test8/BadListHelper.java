package com.chenshun.test.multipthread.test8;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: mew <p />
 * Time: 18/4/28 14:54  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@NotThreadSafe
public class BadListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }

}
