package com.chenshun.test.cglib;

/**
 * User: mew <p />
 * Time: 18/1/30 16:47  <p />
 * Version: V1.0  <p />
 * Description: 自定义一个类加载器，用于将字节码转换为class对 <p />
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass(byte[] b, int off, int len) {
        return super.defineClass(b, off, len);
    }

}
