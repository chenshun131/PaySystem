package wusc.edu.pay.facade.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易码，账户服务专用
 *
 * @author Administrator
 */
public enum AccountTradeTypeEnum {

    SPLIT("分账", 1001),

    ACCOUNT_TRANSFER("会员转账", 2001),

    ACCOUNT_DEPOSIT("会员充值", 3001),

    NET_B2C_REFUND("B2C网银退款", 4001),
    NET_B2B_REFUND("B2B网银退款", 4002),
    DEPOSIT_FAILD_REFUND("充值失败退款", 4003),
    FAST_REFUND("快捷支付退款", 4004),
    ACCOUNT_BALANCE_REFUND("会员余额支付退款", 4005),
    POS_REFUND("POS退货", 4008),
    SPLIT_REFUND("分账退款", 4009),
    CASH_REFUND("现金支付退款", 4010),
    POS_RECHARGE("POS充值", 4013),

    SETTLEMENT("结算", 5001),
    ATM("提现", 5002),
    SETTLEMENT_INTO("结算到账", 5003),
    ATM_INTO("提现到账", 5004),

    NET_B2C_PAY("B2C银行卡支付", 6001),
    NET_B2B_PAY("B2B银行卡支付", 6002),
    FAST_PAY("快捷支付", 6004),
    ACCOUNT_BALANCE_PAY("余额支付", 6005),
    POS_PAY("POS消费", 6006),
    CASH_PAY("现金支付", 6007),

    REMIT("打款", 7001),

    ACCOUNTING_DAILY_CUT("会计日终", 8001),

    ACCOUNT_ADJUST("账户调账", 9001),

    MERCHANT_RECON("商户认账", 1101),
    BANK_MORE_PLAT_RECON("银行长款平台认账", 1102),
    BANK_LESS_PLAT_RECON("银行短款平台认账", 1103),
    BANK_MORE_NOT_MATCH_BANK_RECON("银行长款金额不符银行认账", 1104),
    CASH_PAY_RECON("现金支付入账", 1105),
    ACCOUNT_AGENCYDEBIT("代扣", 1106);

    /** 枚举值 */
    private int value;

    /** 描述 */
    private String desc;

    private AccountTradeTypeEnum(String desc, int value) {
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

    public static AccountTradeTypeEnum getEnum(int value) {
        AccountTradeTypeEnum resultEnum = null;
        AccountTradeTypeEnum[] enumAry = AccountTradeTypeEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].getValue() == value) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AccountTradeTypeEnum[] ary = AccountTradeTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (AccountTradeTypeEnum anAry : ary) {
            Map<String, Object> map = new HashMap<>();
            String key = String.valueOf(getEnum(anAry.getValue()));
            map.put("value", String.valueOf(anAry.getValue()));
            map.put("desc", anAry.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        AccountTradeTypeEnum[] ary = AccountTradeTypeEnum.values();
        List list = new ArrayList();
        for (AccountTradeTypeEnum anAry : ary) {
            Map<String, String> map = new HashMap<>();
            map.put("value", anAry.getValue() + "");
            map.put("desc", anAry.getDesc());
            list.add(map);
        }
        return list;
    }

}
