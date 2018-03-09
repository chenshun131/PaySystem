package com.chenshun.transformer;

public class TestHadoopHomeEnv
{
    public static void main(String[] args)
    {
        // eclipse需要重启
        System.out.println(System.getenv("HADOOP_HOME"));
        System.out.println(System.getProperty("HADOOP_HOME"));
    }
}
