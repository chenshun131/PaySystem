package com.chenshun.test.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: mew <p />
 * Time: 18/1/31 16:17  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    @Test
    public void test1() {
    }

    /**
     * 将java对象转化为byte数组
     *
     * @param report
     * @return
     */
    public byte[] serializeProtoStuffReport(PerformanceReport report) {
        Schema<PerformanceReport> schema = RuntimeSchema.getSchema(PerformanceReport.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(report, schema, buffer);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * 将byte数组写入文件
     *
     * @param bytes
     * @param filepath
     */
    public void byteToFile(byte[] bytes, String filepath) {
        FileOutputStream fos = null;
        File out = new File(filepath);
        if (out.exists()) {
            out.delete();
        }
        try {
            out.createNewFile();
            fos = new FileOutputStream(filepath);
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将序列化的文件读取出来
     *
     * @param filepath
     * @return
     */
    public byte[] fileToBytes(String filepath) {
        Path path = Paths.get(filepath);
        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return data;
        }
    }

    /**
     * 将读取到byte数组反序列化为需要的java对象
     *
     * @param bytesList
     * @return
     */
    public PerformanceReport deserializeProtoStuffReport(byte[] bytesList) {
        Schema<PerformanceReport> schema = RuntimeSchema.getSchema(PerformanceReport.class);
        PerformanceReport product = new PerformanceReport();
        ProtostuffIOUtil.mergeFrom(bytesList, product, schema);
        return product;
    }

}
