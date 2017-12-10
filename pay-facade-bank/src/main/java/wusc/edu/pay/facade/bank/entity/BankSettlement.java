package wusc.edu.pay.facade.bank.entity;

import wusc.edu.pay.common.entity.BaseEntity;

/**
 * @描述: 银行结算信息参数实体
 * @作者: WuShuicheng
 * @创建时间: 2014-4-15, 下午5:54:03
 */
public class BankSettlement extends BaseEntity {

    private static final long serialVersionUID = 6479382076993649504L;

    /** 银行渠道编码 */
    private String bankChannelCode;

    /** 结算周期：T+X */
    private Integer settleCycle;

    /** 手续费账户：关联银行账户表 */
    private String chargeAccount;

    /** 保证金账户：关联银行账户表 */
    private String marginAccount;

    /** 手续费扣收方式：1：内扣、2：外扣 */
    private Integer chargeDeductWay;

    /** 手续费扣收周期：1：实时、2：包年 */
    private Integer chargeDeductCycle;

    /** 手续费支付方式：1：自动扣帐、2：人工转账 */
    private Integer chargePayWay;

    /** 退款方式：1：内扣、2：外扣 */
    private Integer refoundType;

    /** 退款扣收方式：1：接口、2：网银、3：传真、4：邮件、5：邮寄 */
    private Integer refoundDeductWay;

    /** 退款有效期：（X天内允许退款） */
    private Integer refoundValidity;

    /** 是否退回手续费：1：是 、 2：否 */
    private Integer isReturnCharge;

    /** 部分退款是否退回部分手续费：对于支持部分退款的情况 ：1：是 、 2 ：否 */
    private Integer isReturnPartFee;

    /** 退款到账时间（X天后到帐） */
    private Integer refundAccountTime;

    /** 退款限额 */
    private Double refundLimit;

    /** 是否非工作日到账 ，1:是，2:否 */
    private Integer isNonWorkdayAccount;

    /** 备注 */
    private String comments;

    /** 银行渠道名称(只用来显示用，对应数据库没有实际字段) */
    private String bankChannelName;

    /** 是否支持部分退款 1:是 2:否 */
    private Integer isRabates;

    public String getBankChannelName() {
        return bankChannelName;
    }

    public void setBankChannelName(String bankChannelName) {
        this.bankChannelName = bankChannelName;
    }

    public Integer getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(Integer settleCycle) {
        this.settleCycle = settleCycle;
    }

    public String getChargeAccount() {
        return chargeAccount;
    }

    public void setChargeAccount(String chargeAccount) {
        this.chargeAccount = chargeAccount;
    }

    public String getMarginAccount() {
        return marginAccount;
    }

    public void setMarginAccount(String marginAccount) {
        this.marginAccount = marginAccount;
    }

    public Integer getChargeDeductWay() {
        return chargeDeductWay;
    }

    public void setChargeDeductWay(Integer chargeDeductWay) {
        this.chargeDeductWay = chargeDeductWay;
    }

    public Integer getChargeDeductCycle() {
        return chargeDeductCycle;
    }

    public void setChargeDeductCycle(Integer chargeDeductCycle) {
        this.chargeDeductCycle = chargeDeductCycle;
    }

    public Integer getChargePayWay() {
        return chargePayWay;
    }

    public void setChargePayWay(Integer chargePayWay) {
        this.chargePayWay = chargePayWay;
    }

    public Integer getRefoundType() {
        return refoundType;
    }

    public void setRefoundType(Integer refoundType) {
        this.refoundType = refoundType;
    }

    public Integer getRefoundDeductWay() {
        return refoundDeductWay;
    }

    public void setRefoundDeductWay(Integer refoundDeductWay) {
        this.refoundDeductWay = refoundDeductWay;
    }

    public Integer getRefoundValidity() {
        return refoundValidity;
    }

    public void setRefoundValidity(Integer refoundValidity) {
        this.refoundValidity = refoundValidity;
    }

    public Integer getIsReturnCharge() {
        return isReturnCharge;
    }

    public void setIsReturnCharge(Integer isReturnCharge) {
        this.isReturnCharge = isReturnCharge;
    }

    public Integer getIsReturnPartFee() {
        return isReturnPartFee;
    }

    public void setIsReturnPartFee(Integer isReturnPartFee) {
        this.isReturnPartFee = isReturnPartFee;
    }

    public Integer getRefundAccountTime() {
        return refundAccountTime;
    }

    public void setRefundAccountTime(Integer refundAccountTime) {
        this.refundAccountTime = refundAccountTime;
    }

    public Double getRefundLimit() {
        return refundLimit;
    }

    public void setRefundLimit(Double refundLimit) {
        this.refundLimit = refundLimit;
    }

    public Integer getIsNonWorkdayAccount() {
        return isNonWorkdayAccount;
    }

    public void setIsNonWorkdayAccount(Integer isNonWorkdayAccount) {
        this.isNonWorkdayAccount = isNonWorkdayAccount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBankChannelCode() {
        return bankChannelCode;
    }

    public void setBankChannelCode(String bankChannelCode) {
        this.bankChannelCode = bankChannelCode;
    }

    public Integer getIsRabates() {
        return isRabates;
    }

    public void setIsRabates(Integer isRabates) {
        this.isRabates = isRabates;
    }

}
