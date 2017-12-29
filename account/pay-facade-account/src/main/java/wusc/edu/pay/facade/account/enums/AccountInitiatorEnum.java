package wusc.edu.pay.facade.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户操作,发起方
 *
 * @author Administrator
 */
public enum AccountInitiatorEnum {

    RCMS_SYS("风控系统", 1),
    BOSS_SYS("运营BOSS", 2),
    USER_REG("用户注册", 3);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private AccountInitiatorEnum(String desc, int value) {
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

    public static AccountInitiatorEnum getEnum(int value) {
        AccountInitiatorEnum resultEnum = null;
        AccountInitiatorEnum[] enumAry = AccountInitiatorEnum.values();
        for (AccountInitiatorEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AccountInitiatorEnum[] ary = AccountInitiatorEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (AccountInitiatorEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        AccountInitiatorEnum[] ary = AccountInitiatorEnum.values();
        List list = new ArrayList();
        for (AccountInitiatorEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", anAry.toString());
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

}
