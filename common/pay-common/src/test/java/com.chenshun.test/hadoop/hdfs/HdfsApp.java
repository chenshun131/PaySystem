package com.chenshun.test.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * User: mew <p />
 * Time: 18/2/27 12:32  <p />
 * Version: V1.0  <p />
 * Description: <p />
 */
public class HdfsApp {

    public static FileSystem getFileSystem() throws Exception {
        // creat configuration , core-site.xml hdfs-site.xml..
        Configuration configuration = new Configuration();
        // configuration.set("fs.defaultFS", "hdfs://hadoop-senior01.ibeifeng.com:8020");

        // get fileSystem
        return FileSystem.get(configuration);
    }

    public static void read(String fileName) throws Exception {
        FileSystem fileSystem = getFileSystem();

        // read Path
        Path readPath = new Path(fileName);

        // input stream
        FSDataInputStream inStream = fileSystem.open(readPath);
        try {
            IOUtils.copyBytes(inStream, System.out, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(inStream);
        }
    }

    public static void main(String[] args) throws Exception {
        // String fileName = "/user/chenshun/tmp/conf/core-site.xml";
        FileSystem fileSystem = getFileSystem();
        String fileName = "/user/chenshun/tmp/wc.input";
        // write Path
        Path writePath = new Path(fileName);

        // wirte file
        FSDataOutputStream outStream = fileSystem.create(writePath);

        // get input stream
        FileInputStream inStream = new FileInputStream(new File("/opt/datas/wc.input"));
        try {
            IOUtils.copyBytes(inStream, outStream, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(inStream);
            IOUtils.closeStream(outStream);
        }
    }

}
