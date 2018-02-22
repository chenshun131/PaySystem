package wusc.edu.pay.common.utils.date;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/6/16 17:24  <p />
 * Version: V1.0  <p />
 * Description: 日期格式化类型 <p />
 */
public enum DateFormatType {

    DEFAULT_TYPE("yyyy-MM-dd HH:mm:ss"),

    SIMPLE_TYPE("yyyy-MM-dd"),

    FULL_TYPE("yyyyMMddHHmmss"),

    SIMPLE_VIRGULE_TYPE("yyyy/MM/dd"),

    FULL_VIRGULE_TYPE("yyyy/MM/dd HH:mm:ss"),

    SIMPLE_CN_TYPE("yyyy年MM月dd日"),

    FULL_CN_TYPE("yyyy年MM月dd日 HH时mm分ss秒"),

    FUll_TIME_TYPE("HH:mm:ss"),

    HOUR_MINUTE_TIME_TYPE("HH:mm");

    private final String value;

    DateFormatType(String formatStr) {
        this.value = formatStr;
    }

    public String getValue() {
        return this.value;
    }

    public static String[] getAllFormatTypes() {
        DateFormatType[] types = values();
        List<String> values = new ArrayList<>();
        for (DateFormatType type : types) {
            values.add(type.getValue());
        }
        return values.toArray(new String[values.size()]);
    }

}
