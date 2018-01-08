package wusc.edu.pay.facade.remit.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzh
 * @Title: 打款处理
 * @Description:
 * @date 2014-7-22 下午2:12:55
 */
public enum RemitProcessStatusEnum {

    AUTHSTR("待复核", 1),
    PROCESSING("处理中", 2),
    REMIT_SUCCESS("打款成功", 3),
    REMIT_FAILURE("打款失败", 4),
    CANCELED("已撤销", 5),
    REHANDLE("已重出", 6),
    UNAPPROVE("复核未通过", 7),
    YEEPAY_FAILD("平台原因造成的打款失败", 8),
    REMIT_FINISH("银行打款中", 9),
    ONLINE_BANK_WAIT("网银打款待处理", 10);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private RemitProcessStatusEnum(String desc, int value) {
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

    public static RemitProcessStatusEnum getEnum(int value) {
        RemitProcessStatusEnum resultEnum = null;
        RemitProcessStatusEnum[] enumAry = RemitProcessStatusEnum.values();
        for (RemitProcessStatusEnum anEnumAry : enumAry) {
            if (anEnumAry.getValue() == value) {
                resultEnum = anEnumAry;
                break;
            }
        }
        return resultEnum;
    }

    public static List toList() {
        RemitProcessStatusEnum[] ary = RemitProcessStatusEnum.values();
        List list = new ArrayList();
        for (RemitProcessStatusEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

    public static Map<String, Map<String, Object>> toMap() {
        RemitProcessStatusEnum[] ary = RemitProcessStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (RemitProcessStatusEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
