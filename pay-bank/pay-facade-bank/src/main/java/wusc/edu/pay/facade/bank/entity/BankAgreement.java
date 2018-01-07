package wusc.edu.pay.facade.bank.entity;

import wusc.edu.pay.common.entity.BaseEntity;

import java.util.Date;

/**
 * @描述: 银行协议参数实体
 * @作者: WuShuicheng
 * @创建时间: 2013-9-24,下午2:59:26
 * @版本: 1.0
 */
public class BankAgreement extends BaseEntity {

    private static final long serialVersionUID = -5970113939440782900L;

    /** 银行编码，即银行简称，例如：工商银行（ICBC） */
    private String bankCode;

    /** 商户编号，支付平台在银行处的编号，由银行提供 */
    private String merchantNo;

    /** 合同编号，支付平台与银行签订的接口协议编号，由具体合同确定 */
    private String agreementNo;

    /** 银行序号，由支付平台生成的用来区分银行接口的序号 */
    private String bankSequence;

    /** 接口名称，具体根据银行接口名称而定 */
    private String interfaceName;

    /** 大小额：银行区分系统使用大小额的金额限制 */
    private Double amountSystem;

    /** B2B/B2C/快捷支付/批量大款/代收代付,1：B2B、2：B2C、3：快捷支付、4：批量大款、5：代收代付 */
    private Integer linkType;

    /** 通讯方式，HTTP、HTTPS、SFTP */
    private String communicationMode;

    /** 通讯地址，支付平台请求银行的地址 */
    private String communicationAddress;

    /** 合同OA号，协议在支付平台OA系统中的标号 */
    private String contractOANo;

    /** 卡种，借记卡100、信用卡101 */
    private String cardType;

    /** 上线时间，协议正式生效时间 */
    private Date onlineTime;

    /** 下线时间，协议到期时间 */
    private Date offlineTime;

    /** 商户类型，接口支持的允许支付平台接入的商户类型 */
    private String merchantType;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 区（县） */
    private String area;

    /** 银行联系人：名称、类型、电话、邮箱 */
    private String linkMan;

    /** 描述 */
    private String remark;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public String getBankSequence() {
        return bankSequence;
    }

    public void setBankSequence(String bankSequence) {
        this.bankSequence = bankSequence;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Double getAmountSystem() {
        return amountSystem;
    }

    public void setAmountSystem(Double amountSystem) {
        this.amountSystem = amountSystem;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public String getCommunicationMode() {
        return communicationMode;
    }

    public void setCommunicationMode(String communicationMode) {
        this.communicationMode = communicationMode;
    }

    public String getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getContractOANo() {
        return contractOANo;
    }

    public void setContractOANo(String contractOANo) {
        this.contractOANo = contractOANo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
