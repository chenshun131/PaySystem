package wusc.edu.pay.facade.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 冻结资金操作类型
 *
 * @author huqian
 * @version 1.0
 * @date 2014-01-07
 */
public enum AccountFrozenHistoryTypeEnum {

    FROZEN("冻结", 123),
    UNFROZEN("解冻", 321);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private AccountFrozenHistoryTypeEnum(String desc, int value) {
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

    public static AccountFrozenHistoryTypeEnum getEnum(int value) {
        AccountFrozenHistoryTypeEnum resultEnum = null;
        AccountFrozenHistoryTypeEnum[] enumAry = AccountFrozenHistoryTypeEnum.values();
        for (AccountFrozenHistoryTypeEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AccountFrozenHistoryTypeEnum[] ary = AccountFrozenHistoryTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (AccountFrozenHistoryTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        AccountFrozenHistoryTypeEnum[] ary = AccountFrozenHistoryTypeEnum.values();
        List list = new ArrayList();
        for (AccountFrozenHistoryTypeEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", anAry.toString());
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

}
