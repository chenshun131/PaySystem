package wusc.edu.pay.facade.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户资金变动方向
 *
 * @author huqian
 * @version 1.0
 * @date 2014-01-07
 */
public enum AccountFundDirectionEnum {

    ADD("加款", 123),
    SUB("减款", 321),
    FROZEN("冻结", 321),
    UNFROZEN("解冻", 123);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private AccountFundDirectionEnum(String desc, int value) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "value=" + this.value + " desc=" + this.desc;
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

    public static AccountFundDirectionEnum getEnum(int value) {
        AccountFundDirectionEnum resultEnum = null;
        AccountFundDirectionEnum[] enumAry = AccountFundDirectionEnum.values();
        for (AccountFundDirectionEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AccountFundDirectionEnum[] ary = AccountFundDirectionEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (AccountFundDirectionEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        AccountFundDirectionEnum[] ary = AccountFundDirectionEnum.values();
        List list = new ArrayList();
        for (AccountFundDirectionEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", anAry.toString());
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }


}
