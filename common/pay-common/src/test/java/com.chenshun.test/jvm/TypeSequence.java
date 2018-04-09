package com.chenshun.test.jvm;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * User: mew <p />
 * Time: 18/4/9 10:51  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TypeSequence {

    @Contended
    private boolean contended_boolean;

    private volatile byte a;

    private volatile boolean b;

    @Contended
    private int contended_short;

    private volatile char d;

    private volatile short c;

    private volatile int e;

    private volatile float f;

    @Contended
    private int contended_int;

    @Contended
    private double contended_double;

    private volatile double g;

    private volatile long h;

    public static Unsafe UNSAFE;

    static {
        try {
            @SuppressWarnings("ALL")
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        System.out.println("e:int    \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("e")));
        System.out.println("g:double \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("g")));
        System.out.println("h:long   \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("h")));
        System.out.println("f:float  \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("f")));
        System.out.println("c:short  \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("c")));
        System.out.println("d:char   \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("d")));
        System.out.println("a:byte   \t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("a")));
        System.out.println("b:boolean\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("b")));

        System.out.println("contended_boolean:boolean\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("contended_boolean")));
        System.out.println("contended_short:short\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("contended_short")));
        System.out.println("contended_int:int\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("contended_int")));
        System.out.println("contended_double:double\t" + UNSAFE.objectFieldOffset(TypeSequence.class.getDeclaredField("contended_double")));
    }

}
