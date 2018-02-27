package com.chenshun.test.hadoop.hive;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * User: chenshun131 <p />
 * Time: 16/11/18 00:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TrimUDF extends UDF {

    public Text evaluate(Text str) {
        if (str == null) {
            return null;
        }
        String text = str.toString();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        text = text.replaceAll("\"", "");
        return new Text(text);
    }

    public static void main(String[] args) {
        System.out.println(new TrimUDF().evaluate(new Text("\"27.38.5.159\" \"/course/view.php?id=27\"")));
    }

}
