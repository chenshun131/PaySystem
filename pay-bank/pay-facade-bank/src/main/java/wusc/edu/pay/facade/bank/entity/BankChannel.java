package wusc.edu.pay.facade.bank.entity;

import wusc.edu.pay.common.entity.BaseEntity;
import wusc.edu.pay.common.enums.BankCode;

/**
 * @描述: 银行渠道参数实体
 * @作者: HuQian, WuShuicheng
 * @创建时间: 2014-4-15, 下午3:48:52
 */
public class BankChannel extends BaseEntity {

    private static final long serialVersionUID = -9144042321324982118L;

    /** 银行渠道编号：系统自动生成 */
    private String bankChannelCode;

    /** 银行渠道名称：系统自动生成 */
    private String bankChannelName;

    /** 银行名称 */
    private String bankName;

    /** 银行编号：银行简称，例如：工商银行(ICBC) */
    private BankCode bankCode;

    /** 状态:100:激活 101:冻结 */
    private Integer status;

    /** 落地行名称：具体到支行 */
    private String landingBankName;

    /** 银行协议ID */
    private Long bankAgreementId;

    /** 银行账户ID */
    private Long bankAccountId;

    /** 描述 */
    private String remark;

    /** 银行序号（非数据表映射字段，只用于展示数据） */
    private String bankSequence;

    /** 银行账户名称 （非数据表映射字段，只用于展示数据） */
    private String bankAccountName;

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    // --------along add ---------
    private Double bankRateOrFee;

    private String province;

    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getBankRateOrFee() {
        return bankRateOrFee;
    }

    public void setBankRateOrFee(Double bankRateOrFee) {
        this.bankRateOrFee = bankRateOrFee;
    }

    // -----------along add------------------

    public String getBankChannelCode() {
        return bankChannelCode;
    }

    public void setBankChannelCode(String bankChannelCode) {
        this.bankChannelCode = bankChannelCode;
    }

    public String getBankChannelName() {
        return bankChannelName;
    }

    public void setBankChannelName(String bankChannelName) {
        this.bankChannelName = bankChannelName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BankCode getBankCode() {
        return bankCode;
    }

    public void setBankCode(BankCode bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLandingBankName() {
        return landingBankName;
    }

    public void setLandingBankName(String landingBankName) {
        this.landingBankName = landingBankName;
    }

    public Long getBankAgreementId() {
        return bankAgreementId;
    }

    public void setBankAgreementId(Long bankAgreementId) {
        this.bankAgreementId = bankAgreementId;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankSequence() {
        return bankSequence;
    }

    public void setBankSequence(String bankSequence) {
        this.bankSequence = bankSequence;
    }

}
