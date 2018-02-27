package com.chenshun.test.hadoop.hive;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Step 1:
 * 1. Implement one or more methods named "evaluate" which will be called by Hive.
 * 2."evaluate" should never be a void method. However it can return "null" if needed.
 */
public class DateFormatUDF extends UDF {

    // [30/Oct/2016:17:38:20 +0800]
    public static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);

    // 20161030173820
    public static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 实现单词转换为小写字母
     *
     * @param str
     * @return
     */
    public Text evaluate(Text str) {
        Text output = new Text();

        // invalidate
        if (null == str) {
            return null;
        }

        if (StringUtils.isBlank(str.toString())) {
            return null;
        }

        try {
            String value = str.toString().trim().replace("[", "").replace("]", "");
            // parse
            Date parseDate = INPUT_FORMAT.parse(value);
            // format
            String formatValue = OUTPUT_FORMAT.format(parseDate);

            // set output
            output.set(formatValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 转小写
        return output;
    }


    public static void main(String[] args) {
        System.out.print(new DateFormatUDF().evaluate(new Text("[30/Oct/2016:17:38:20 +0800]")));

        // 20161030173820
        // [30/Oct/2016:17:38:20 +0800]
    }
}
