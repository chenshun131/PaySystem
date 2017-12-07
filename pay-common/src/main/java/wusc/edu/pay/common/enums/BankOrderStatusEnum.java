package wusc.edu.pay.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BankOrderStatusEnum {

    ORDER_NONEPROCESS("未处理", 0),
    ORDER_SUSSESS("支付成功", 1),
    ORDER_FAIL("支付失败", 2),
    ORDER_PROCESSING("处理中", 3),
    ORDER_ERR("未知异常", 4);

    private BankOrderStatusEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    /*枚举值*/
    private int value;

    /*描述*/
    private String desc;

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

    public static BankOrderStatusEnum getEnum(int value) {
        BankOrderStatusEnum resultEnum = null;
        BankOrderStatusEnum[] enumAry = BankOrderStatusEnum.values();
        for (BankOrderStatusEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static List toList() {
        BankOrderStatusEnum[] ary = BankOrderStatusEnum.values();
        List list = new ArrayList();
        for (BankOrderStatusEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

    public static Map<String, Map<String, Object>> toMap() {
        BankOrderStatusEnum[] ary = BankOrderStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (BankOrderStatusEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
