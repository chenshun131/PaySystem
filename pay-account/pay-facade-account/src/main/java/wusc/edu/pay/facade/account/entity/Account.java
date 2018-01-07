package wusc.edu.pay.facade.account.entity;

import wusc.edu.pay.common.entity.BaseEntity;
import wusc.edu.pay.common.utils.number.AmountUtil;
import wusc.edu.pay.facade.account.enums.AccountStatusEnum;
import wusc.edu.pay.facade.account.exception.AccountBizException;

import java.util.Date;

/**
 * @author Administrator
 */
public class Account extends BaseEntity {

    private static final long serialVersionUID = -1209632513216352308L;

    /** 账户编号 */
    private String accountNo;

    /** 用户编号 */
    private String userNo;

    /** 账户状态 */
    private Integer status;

    /** 账户余额 */
    private Double balance = 0D;

    /** 不可用余额 */
    private Double unBalance = 0D;

    /** 保证金 */
    private Double securityMoney = 0D;

    /** 账户类型 */
    private Integer accountType;

    /** 最后更新时间 */
    private Date lastTime = new Date();

    /** 可结算金额 */
    private Double availableSettAmount = 0D;

    /** 会计科目代码 */
    private String accountTitleNo;

    public String getAccountNo() {
        return accountNo.trim();
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getUnBalance() {
        return unBalance;
    }

    public void setUnBalance(Double unBalance) {
        this.unBalance = unBalance;
    }

    public Double getSecurityMoney() {
        return securityMoney;
    }

    public void setSecurityMoney(Double securityMoney) {
        this.securityMoney = securityMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getAccountTitleNo() {
        return accountTitleNo;
    }

    public void setAccountTitleNo(String accountTitleNo) {
        this.accountTitleNo = accountTitleNo;
    }

    public Double getAvailableSettAmount() {
        return availableSettAmount;
    }

    public void setAvailableSettAmount(Double availableSettAmount) {
        this.availableSettAmount = availableSettAmount;
    }

    /**
     * 存入
     *
     * @param amount
     */
    public void credit(Double amount) {
        if (this.status.intValue() == AccountStatusEnum.INACTIVE.getValue()
                || this.status.intValue() == AccountStatusEnum.CANCELLED.getValue()
                || this.status.intValue() == AccountStatusEnum.INACTIVE_FREEZE_CREDIT.getValue()) {
            throw AccountBizException.ACCOUNT_STATUS_IS_INACTIVE.newInstance("账户状态异常,用户编号{%s},账户状态{%s}", userNo, this.status.intValue()).print();
        }
        this.lastTime = new Date();
        this.setBalance(AmountUtil.add(this.balance, amount));
    }

    /**
     * 支出
     *
     * @param amount
     */
    public void debit(Double amount) {
        if (this.status.intValue() == AccountStatusEnum.INACTIVE.getValue()
                || this.status.intValue() == AccountStatusEnum.CANCELLED.getValue()
                || this.status.intValue() == AccountStatusEnum.INACTIVE_FREEZE_DEBIT.getValue()) {
            throw AccountBizException.ACCOUNT_STATUS_IS_INACTIVE.newInstance("账户状态异常,用户编号{%s},账户状态{%s}", userNo, this.status.intValue()).print();
        }

        if (!this.availableBalanceIsEnough(amount)) {
            throw AccountBizException.ACCOUNT_AVAILABLEBALANCE_IS_NOT_ENOUGH.print();
        }
        this.lastTime = new Date();
        this.setBalance(AmountUtil.sub(this.balance, amount));
    }

    /**
     * 资金冻结
     *
     * @param frozenAmount
     */
    public void frozen(Double frozenAmount) {
        if (!this.availableBalanceIsEnough(frozenAmount)) {
            throw AccountBizException.ACCOUNT_AVAILABLEBALANCE_IS_NOT_ENOUGH.print();
        }
        this.lastTime = new Date();
        this.setUnBalance(AmountUtil.add(this.unBalance, frozenAmount));
    }

    /**
     * 资金解冻
     *
     * @param frozenAmount
     */
    public void unFrozen(Double frozenAmount) {
        if (AmountUtil.bigger(frozenAmount, this.unBalance)) {
            throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT.print();
        }
        this.lastTime = new Date();
        this.setUnBalance(AmountUtil.sub(this.unBalance, frozenAmount));
    }

    /**
     * 获取可用余额
     *
     * @return
     */
    public Double getAvailableBalance() {
        return AmountUtil.sub(this.balance, this.unBalance);
    }

    /**
     * 验证可用余额是否足够
     *
     * @param amount
     * @return
     */
    public boolean availableBalanceIsEnough(double amount) {
        return AmountUtil.greaterThanOrEqualTo(this.getAvailableBalance(), amount);
    }

}
