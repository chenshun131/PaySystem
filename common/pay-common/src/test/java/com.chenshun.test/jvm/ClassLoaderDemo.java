package com.chenshun.test.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 16:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                // com.chenshun.test.jvm.ClassLoaderDemo
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream ins = getClass().getResourceAsStream(fileName);
                if (ins == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] buff = new byte[ins.available()];
                    ins.read(buff);

                    return defineClass(name, buff, 0, buff.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };
        // 此处 com.chenshun.test.jvm.ClassLoaderDemo 会被加载两边，System ClassLoader 和 User Define ClassLoade
        Object c = classLoader.loadClass("com.chenshun.test.jvm.ClassLoaderDemo").newInstance();
        System.out.println(c.getClass()); // 输出 class com.chenshun.test.jvm.ClassLoaderDemo
        System.out.println(c instanceof ClassLoaderDemo); // false 使用的类加载器不同，因此是不同类
    }

}
