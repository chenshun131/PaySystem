package com.chenshun.test.hadoop.hive;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * User: chenshun131 <p />
 * Time: 16/11/17 22:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpperLowerUDF extends UDF {

    /**
     * 实现单词转换为小写
     *
     * @param str
     * @return
     */
    public Text evaluate(Text str) {
        // invalidate
        if (null == str) {
            return null;
        }

        if (StringUtils.isBlank(str.toString())) {
            return null;
        }
        // 转小写
        return new Text(str.toString().toLowerCase());
    }

    /**
     * @param str
     *         单词
     * @param flag
     *         标记 0:大写 1:小写
     * @return
     */
    public Text evaluate(Text str, IntWritable flag) {
        // invalidate
        if (null == str) {
            return null;
        }
        if (StringUtils.isBlank(str.toString())) {
            return null;
        }
        if (flag == null) {
            return null;
        }
        // 转化为大写
        if (0 == flag.get()) {
            return new Text(str.toString().toUpperCase());
        }
        // 转小写
        if (1 == flag.get()) {
            return new Text(str.toString().toLowerCase());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new UpperLowerUDF().evaluate(new Text("Hadoop XI XI HA HA")));
        System.out.println(new UpperLowerUDF().evaluate(new Text("HADOOOOO")));
    }

}
