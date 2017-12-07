package wusc.edu.pay.common.enums.ca;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: CAEnum <br/>
 * Function: <br/>
 * date: 2014-1-8 下午6:38:02 <br/>
 *
 * @author laich
 */
public enum CAEnum {

    APPLY("已申请", 0), REGULAR("正常状态", 1), EXPIRED("过期", 2), REVOKE("吊销", 3);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private CAEnum(String desc, int value) {
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

    public static CAEnum getEnum(int value) {
        CAEnum resultEnum = null;
        CAEnum[] enumAry = CAEnum.values();
        for (CAEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    /**
     * 枚举转map.<br/>
     *
     * @return
     */
    public static Map<String, Map<String, Object>> toMap() {
        CAEnum[] ary = CAEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (CAEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
