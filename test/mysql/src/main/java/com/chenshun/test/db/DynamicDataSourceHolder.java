package com.chenshun.test.db;

/**
 * User: chenshun131 <p />
 * Time: 18/3/15 21:20  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DynamicDataSourceHolder {

    public static final String MASTER = "master";

    public static final String SLAVE = "slave";

    /** 数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰 */
    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<>();

    public static void setDataSource(String name) {
        THREAD_DATA_SOURCE.set(name);
    }

    public static String getDataSource() {
        return THREAD_DATA_SOURCE.get();
    }

    public static void clearDataSource() {
        THREAD_DATA_SOURCE.remove();
    }

}
