package wusc.edu.pay.common.enums.ca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CAEnum <br/>
 * Function: <br/>
 * date: 2014-1-8 下午6:38:02 <br/>
 *
 * @author laich
 */
public enum CAAddrEnum {

    OFFICE("办公室", 1), HOME("家里", 2), OTHER("其它地方", 3);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private CAAddrEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static List toList() {
        CAAddrEnum[] ary = CAAddrEnum.values();
        List<Map<String, String>> list = new ArrayList<>();
        for (CAAddrEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

    public static CAAddrEnum getEnum(int value) {
        CAAddrEnum resultEnum = null;
        CAAddrEnum[] enumAry = CAAddrEnum.values();
        for (CAAddrEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
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

    /**
     * 枚举转map
     *
     * @return
     */
    public static Map<String, Map<String, Object>> toMap() {
        CAAddrEnum[] ary = CAAddrEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (CAAddrEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
