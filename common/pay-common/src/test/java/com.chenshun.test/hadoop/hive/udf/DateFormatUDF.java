package com.chenshun.test.hadoop.hive.udf;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Step 1:
 *      1. Implement one or more methods named "evaluate" which will be called by Hive.
 *      2."evaluate" should never be a void method. However it can return "null" if needed.
 */
public class DateFormatUDF extends UDF{
    // 30/Oct/2016:17:38:20
    public static SimpleDateFormat INPUTDATE = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    // 20161030173820
    public static SimpleDateFormat OUTPUTDATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期格式的装换
     * @param str
     * @return
     */
    public Text evaluate(Text str){
        // invalidate
        Text out = new Text();
        if(str == null){
            return null;
        }
        //
        if (StringUtils.isBlank(str.toString())){
            return null;
        }

        try {
            String value = str.toString().trim().replace("[","").replace("]","") ;
            // parse
            Date parasedate = INPUTDATE.parse(value);
            // format
            String output = OUTPUTDATE.format(parasedate);
            // set output
            out.set(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * MAIN METHOD
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(
            new DateFormatUDF().evaluate(
                    new Text("[30/Oct/2016:17:38:20 +0800]") //[30/Oct/2016:17:38:20 +0800]
            )
        );
    }

}
