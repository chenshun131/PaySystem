package wusc.edu.pay.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志中操作类型
 *
 * @author shenjialong
 * @desc
 * @date 2014-2-14,上午10:29:50
 */
public enum OperatorLogActionTypeEnum {

    ADDACTION("增加操作", 1),
    UPDATEACTION("修改操作", 2),
    DELETEACTION("删除操作", 3),
    QUERYACTION("查询操作", 4),
    LOGINACTION("登录操作", 5);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private OperatorLogActionTypeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static OperatorLogActionTypeEnum getEnum(int value) {
        OperatorLogActionTypeEnum resultEnum = null;
        OperatorLogActionTypeEnum[] enumAry = OperatorLogActionTypeEnum.values();
        for (OperatorLogActionTypeEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        OperatorLogActionTypeEnum[] ary = OperatorLogActionTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (OperatorLogActionTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        OperatorLogActionTypeEnum[] ary = OperatorLogActionTypeEnum.values();
        List list = new ArrayList();
        for (OperatorLogActionTypeEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

}
