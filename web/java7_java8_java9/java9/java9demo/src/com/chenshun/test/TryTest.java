package com.chenshun.test;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 17:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TryTest {

    private static final String SOURCE = "/Users/chenshun131/Desktop/a.txt";

    private static final String TARGET = "/Users/chenshun131/Desktop/b.txt";

    // 最普通的异常捕获
    @Test
    public void testTry1() {
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(SOURCE);
            fos = new FileOutputStream(TARGET);

            byte[] buf = new byte[8192];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Java7 新特性，支持使用 try 后面跟随 () 管理释放资源，不用显式的处理资源的关闭，要求资源对象的实例化，必须放在try的一对()内完成，
    // 多个资源之间使用 ; 隔开
    @Test
    public void testTry2() {
        try (FileInputStream fis = new FileInputStream(SOURCE);
             FileOutputStream fos = new FileOutputStream(TARGET)) {
            byte[] buf = new byte[8192];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // java 9 中 : 可以在try()中调用已经实例化的资源对象
    //如下的操作不可以在jdk 8 及之前的版本中使用
    @Test
    public void testTry3() {
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try (reader; writer) {
            //此时的reader是final的，不可再被赋值
//            reader = null;

            //读取数据的过程：略
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}