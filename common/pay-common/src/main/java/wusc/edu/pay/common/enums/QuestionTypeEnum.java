package wusc.edu.pay.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：问题分类枚举
 *
 * @version 1.0
 * @author: huangbin
 * @date： 日期：2014-3-7 时间：下午3:52:10
 */
public enum QuestionTypeEnum {
    BUSINESS_CONSULT("业务咨询", 1),
    SERVICE_COMPLAINTS("服务投诉", 2),
    PRODUCT_COMPLAINTS("产品投诉", 3),
    OTHER("其他", 4);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private QuestionTypeEnum(String desc, int value) {
        this.value = value;
        this.desc = desc;
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

    public static QuestionTypeEnum getEnum(int value) {
        QuestionTypeEnum resultEnum = null;
        QuestionTypeEnum[] enumAry = QuestionTypeEnum.values();
        for (QuestionTypeEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        QuestionTypeEnum[] ary = QuestionTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (QuestionTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        QuestionTypeEnum[] ary = QuestionTypeEnum.values();
        List list = new ArrayList();
        for (QuestionTypeEnum anAry : ary) {
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
        QuestionTypeEnum[] enums = QuestionTypeEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (QuestionTypeEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'")
                    .append(senum.toString())
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
