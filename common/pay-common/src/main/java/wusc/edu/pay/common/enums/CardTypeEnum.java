package wusc.edu.pay.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述: 证件类型.
 * @创建: 2014-6-18,下午2:05:23
 * @版本: V1.0
 */
public enum CardTypeEnum {

    ID_CARD("身份证", 1),
    CERTIFICATE_OF_OFFICERS("军官证", 2),
    SOLDIERS("士兵证", 3),
    PASSPORT("护照", 4),
    HKPermit("港澳台居民往来通行证", 5),
    TEMPORARY_IDCARD("临时身份证", 6),
    HOUSEHOLD("户口本", 7),
    OFFICERS_CARD("警官证", 8),
    RESIDENCE_CERTIFICATE("外国人永久居留证", 9),
    OTHER("其他", 10),
    FOREIGN_PASSPORT("外国护照", 11);

    private String desc;

    private int value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    CardTypeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static CardTypeEnum getEnum(int value) {
        CardTypeEnum resultEnum = null;
        CardTypeEnum[] enumAry = CardTypeEnum.values();
        for (CardTypeEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        CardTypeEnum[] ary = CardTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (CardTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
