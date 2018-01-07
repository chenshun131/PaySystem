package wusc.edu.pay.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: CurrencyTypeEnum <br/>
 * Function:  货币类型枚举<br/>
 * date: 2014-7-11 下午12:00:43 <br/>
 *
 * @author laich
 */
public enum CurrencyTypeEnum {

    RMB("人民币", 1), DOLLAR("美元", 2);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private CurrencyTypeEnum(String desc, int value) {
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

    public static CurrencyTypeEnum getEnum(int value) {
        CurrencyTypeEnum resultEnum = null;
        CurrencyTypeEnum[] enumAry = CurrencyTypeEnum.values();
        for (CurrencyTypeEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        CurrencyTypeEnum[] ary = CurrencyTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (CurrencyTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
