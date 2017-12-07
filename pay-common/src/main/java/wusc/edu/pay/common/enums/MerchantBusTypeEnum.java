package wusc.edu.pay.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: 商户业务类型枚举. <br/>
 * 适用于表：
 * @作者: WuShuicheng
 * @创建时间: 2013-9-12,上午11:16:23
 * @版本: 1.0
 */
public enum MerchantBusTypeEnum {

    ONLINE_MERCHANT("在线商户", 1),
    POS_MERCHANT("POS商户", 2),
    MOBILE_MERCHANT("移动商户", 3);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private MerchantBusTypeEnum(String desc, int value) {
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

    public static MerchantBusTypeEnum getEnum(int value) {
        MerchantBusTypeEnum resultEnum = null;
        MerchantBusTypeEnum[] enumAry = MerchantBusTypeEnum.values();
        for (MerchantBusTypeEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static String getName(int value) {
        String result = null;
        MerchantBusTypeEnum[] enumAry = MerchantBusTypeEnum.values();
        for (MerchantBusTypeEnum anEnumAry : enumAry) {
            if (value == anEnumAry.getValue()) {
                result = anEnumAry.name();
                break;
            }
        }
        return result;
    }

    public static String getDesc(int value) {
        String result = null;
        MerchantBusTypeEnum[] enumAry = MerchantBusTypeEnum.values();
        for (MerchantBusTypeEnum anEnumAry : enumAry) {
            if (value == anEnumAry.getValue()) {
                result = anEnumAry.getDesc();
                break;
            }
        }
        return result;
    }

    public static Map<String, Map<String, Object>> toMap() {
        MerchantBusTypeEnum[] ary = MerchantBusTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (MerchantBusTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        MerchantBusTypeEnum[] ary = MerchantBusTypeEnum.values();
        List list = new ArrayList();
        for (MerchantBusTypeEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

}
