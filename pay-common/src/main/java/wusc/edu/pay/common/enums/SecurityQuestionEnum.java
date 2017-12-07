package wusc.edu.pay.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安全问题枚举
 *
 * @author liliqiong
 * @version 1.0
 * @date 2013-10-20
 */
public enum SecurityQuestionEnum {

    QUESTION_1("你母亲的名字是什么？", 1),
    QUESTION_2("你父亲的名字是什么？", 2),
    QUESTION_3("你所读的第一所小学是什么？", 3),
    QUESTION_4("你高中班主任的名字是什么？", 4),
    QUESTION_5("你最喜欢吃的水果是什么？", 5),
    QUESTION_6("你最喜欢的一部电影是什么？", 6);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private SecurityQuestionEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityQuestionEnum getEnum(int value) {
        SecurityQuestionEnum resultEnum = null;
        SecurityQuestionEnum[] enumAry = SecurityQuestionEnum.values();
        for (SecurityQuestionEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        SecurityQuestionEnum[] ary = SecurityQuestionEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (SecurityQuestionEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        SecurityQuestionEnum[] ary = SecurityQuestionEnum.values();
        List list = new ArrayList();
        for (SecurityQuestionEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr() {
        SecurityQuestionEnum[] enums = SecurityQuestionEnum.values();
        StringBuilder jsonStr = new StringBuilder("[");
        for (SecurityQuestionEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'")
                    .append(senum)
                    .append("',desc:'")
                    .append(senum.getDesc())
                    .append("',value:'")
                    .append(senum.getValue())
                    .append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

}
