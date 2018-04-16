package com.chenshun.test.atomic;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: mew <p />
 * Time: 18/4/13 13:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test1 {

    @Test
    public void test1() {
        // 默认值是 false
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        boolean result = atomicBoolean.get();
        System.out.println("result = " + result); // result = false

        boolean oldValue = atomicBoolean.getAndSet(true);
        System.out.println("oldValue = " + oldValue); // oldValue = false
        System.out.println("newValue = " + atomicBoolean.get()); // newValue = true
    }

    @Test
    public void test2() {
        AtomicReference<String> atomicReference = new AtomicReference<>();
        atomicReference.set("ABC-DEF");
        System.out.println(atomicReference.get()); // ABC-DEF

        String initialReference = "initial value referenced";
        AtomicReference<String> atomicStringReference = new AtomicReference<>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged); // exchanged: true

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged); // exchanged: false
    }

    @Test
    public void test3() {
        Unsafe unsafe = Unsafe.getUnsafe();
    }

    @Test
    public void test4() {
        AtomicReference<String> result = new AtomicReference<>();
        String question = "i have some question";
        List<String> engines = Arrays.asList("A", "B", "C", "D", "E");
        for (String base : engines) {
            String url = base + question;
            new Thread(() -> result.compareAndSet(null, url)).start();
        }
        System.out.print(result.get()); // Ai have some question
    }

}
