package wusc.edu.pay.facade.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: 账户状态. 适用于账户表.
 * @作者: chenjh .
 * @创建时间: 2013-8-29,下午1:49:34 .
 * @版本: 1.0 .
 */
public enum AccountStatusEnum {

    ACTIVE("激活", 100),
    INACTIVE("冻结", 101),
    INACTIVE_FREEZE_CREDIT("冻结止收", 102),
    INACTIVE_FREEZE_DEBIT("冻结止付", 103),
    CANCELLED("注销", 104);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private AccountStatusEnum(String desc, int value) {
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

    public static AccountStatusEnum getEnum(int value) {
        AccountStatusEnum resultEnum = null;
        AccountStatusEnum[] enumAry = AccountStatusEnum.values();
        for (AccountStatusEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AccountStatusEnum[] ary = AccountStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (AccountStatusEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        AccountStatusEnum[] ary = AccountStatusEnum.values();
        List list = new ArrayList();
        for (AccountStatusEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

    /**
     * 只要冻结，冻结止收，冻结止付3种状态
     *
     * @return
     */
    public static List toAccountStatusList() {
        AccountStatusEnum[] ary = AccountStatusEnum.values();
        List list = new ArrayList();
        int value[] = {101, 102, 103};
        for (AccountStatusEnum anAry : ary) {
            int val = anAry.getValue();
            for (int v : value) {
                if (val == v) {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", String.valueOf(val));
                    map.put("desc", anAry.getDesc());
                    list.add(map);
                }
            }
        }
        return list;
    }

}
