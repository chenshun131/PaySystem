package com.chenshun.test.file;

import wusc.edu.pay.common.utils.FileUtils;

import java.io.File;

/**
 * User: chenshun131 <p />
 * Time: 18/5/20 13:47  <p />
 * Version: V1.0  <p />
 * Description: 删除 target 目录 <p />
 */
public class Test1 {

    private static final String BASE_PATH = "/Users/chenshun131/Desktop/第170-179节：课件资料";

    public static void main(String[] args) {
        File file = new File(BASE_PATH);
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                for (File innerFile : f.listFiles()) {
                    if ("代码".equals(innerFile.getName())) {
                        for (File codeFile : innerFile.listFiles()) {
                            for (File deleteParentFile : innerFile.listFiles()) {
                                if (deleteParentFile.listFiles() != null && deleteParentFile.listFiles().length > 0) {
                                    for (File deleteFile : deleteParentFile.listFiles()) {
                                        if (deleteFile.isDirectory() && "target".equals(deleteFile.getName())) {
                                            FileUtils.deleteFile(deleteFile);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

}
