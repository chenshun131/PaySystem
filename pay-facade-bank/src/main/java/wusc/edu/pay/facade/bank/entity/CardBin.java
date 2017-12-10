package wusc.edu.pay.facade.bank.entity;

import wusc.edu.pay.common.entity.BaseEntity;

import java.util.Date;

/**
 * @描述: 银行卡BIN参数实体
 * @作者: WuShuicheng
 * @创建时间: 2014-4-16, 上午11:20:36
 */
public class CardBin extends BaseEntity {

    private static final long serialVersionUID = 2010136841282073484L;

    /** 卡BIN */
    private String cardBin;

    /** 发卡行代码 */
    private String bankCode;

    /** 发卡行名称 */
    private String bankName;

    /** 卡名 */
    private String cardName;

    /** 卡种:1借记卡,2贷记卡,3准贷记卡,4预付费卡 */
    private Integer cardKind;

    /** 卡片长度 */
    private Integer cardLength;

    /** 状态:101无效、100有效 */
    private Integer status;

    /** 最后修改人 */
    private Long lastUpdatorId;

    /** 最后修改时间 */
    private Date lastUpdateTime;

    /** 最后修改人姓名 */
    private String lastUpdatorName;

    public String getLastUpdatorName() {
        return lastUpdatorName;
    }

    public void setLastUpdatorName(String lastUpdatorName) {
        this.lastUpdatorName = lastUpdatorName;
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardKind() {
        return cardKind;
    }

    public void setCardKind(Integer cardKind) {
        this.cardKind = cardKind;
    }

    public Integer getCardLength() {
        return cardLength;
    }

    public void setCardLength(Integer cardLength) {
        this.cardLength = cardLength;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLastUpdatorId() {
        return lastUpdatorId;
    }

    public void setLastUpdatorId(Long lastUpdatorId) {
        this.lastUpdatorId = lastUpdatorId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
