package wusc.edu.pay.facade.pms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: 操作日志状态.
 * @作者: WuShuicheng.
 * @创建: 2014-9-22,下午4:35:55
 * @版本: V1.0
 */
public enum PmsOperatorLogStatusEnum {

    SUCCESS("操作成功", 100),
    ERROR("操作失败", 101);

    /** 描述 */
    private String desc;

    /** 枚举值 */
    private int value;

    private PmsOperatorLogStatusEnum(String desc, int value) {
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

    public static PmsOperatorLogStatusEnum getEnum(int value) {
        PmsOperatorLogStatusEnum resultEnum = null;
        PmsOperatorLogStatusEnum[] enumAry = PmsOperatorLogStatusEnum.values();
        for (PmsOperatorLogStatusEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PmsOperatorLogStatusEnum[] ary = PmsOperatorLogStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(ary[num].getValue()));
            map.put("value", String.valueOf(ary[num].getValue()));
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toList() {
        PmsOperatorLogStatusEnum[] ary = PmsOperatorLogStatusEnum.values();
        List list = new ArrayList();
        for (PmsOperatorLogStatusEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

}
