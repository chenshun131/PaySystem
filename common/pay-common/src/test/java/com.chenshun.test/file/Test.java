package com.chenshun.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * User: chenshun131 <p />
 * Time: 18/4/1 17:22  <p />
 * Version: V1.0  <p />
 * Description: 在文件 a.txt 中包含 GBK 的数据，现在转码成 UTF8 写到一个新的文件 b.txt <p />
 */
public class Test {

    private final String OLD_FILE_PATH = "/Users/chenshun131/Desktop/a.txt";

    private final String NEW_FILE_PATH = "/Users/chenshun131/Desktop/b.txt";

    @org.junit.Test
    public void createFile() throws Exception {
        File file = new File(OLD_FILE_PATH);
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write("这是写入文件中的内容".getBytes("GBK"));
        fos.flush();
        fos.close();

        file = new File(NEW_FILE_PATH);
        file.createNewFile();
    }

    /**
     * 使用 io 方式
     *
     * @throws Exception
     */
    @org.junit.Test
    public void io() throws Exception {
        FileInputStream fis = new FileInputStream(new File(OLD_FILE_PATH));
        byte[] content = new byte[2048];
        fis.read(content);
        String str = new String(content, "GBK");

        FileOutputStream fos = new FileOutputStream(NEW_FILE_PATH);
        fos.write(str.getBytes("UTF8"));
        fos.flush();
        fos.close();
    }

    /**
     * 使用 nio 方式
     */
    @org.junit.Test
    public void nio() {
        String str = "";
        try (FileInputStream fis = new FileInputStream(new File(OLD_FILE_PATH))) {
            FileChannel fc1 = fis.getChannel();
            ByteBuffer buf1 = ByteBuffer.allocate(1024);
            fc1.read(buf1);
            str = new String(buf1.array(), "GBK");
        } catch (Exception ignored) {
        }

        try (FileOutputStream fos = new FileOutputStream(new File(NEW_FILE_PATH))) {
            FileChannel fc2 = fos.getChannel();
            ByteBuffer buf2 = Charset.forName("UTF8").encode(str);
            fc2.write(buf2);
        } catch (Exception ignored) {
        }
    }

}
