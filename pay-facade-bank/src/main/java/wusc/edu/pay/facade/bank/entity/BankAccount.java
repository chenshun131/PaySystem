package wusc.edu.pay.facade.bank.entity;

import wusc.edu.pay.common.entity.BaseEntity;

import java.util.Date;


/**
 * @描述: 银行账户信息表参数实体.
 * @作者: WuShuicheng .
 * @创建时间: 2014-4-15, 下午2:31:56
 */
public class BankAccount extends BaseEntity {

    private static final long serialVersionUID = 6258550876950539637L;

    /** 开户银行：具体的银行名称 */
    private String openBank;

    /** 开户行地址：具体到开户支行地址 */
    private String openBankAddress;

    /** 开户日期：账户开户的日期 */
    private Date opendate;

    /** 银行账号：具体的账户编号 */
    private String bankAccount;

    /** 银行行号：具体视银行支行行号，确定与银联系统数据相同 */
    private String bankNo;

    /** 银行账户名称：具体的银行账户名称 */
    private String userName;

    /** 开户经办人：具体经办人 */
    private String operator;

    /** 合作方式：1：存管银行、2：合作银行 */
    private Integer cooperationWay;

    /** 账户性质：1：备付金存管账户、2：自有资金账户、3：备付金收付账户 、4：备付金汇缴账户 */
    private Integer accountNature;

    /** 账户状态：1：正常、2：待销户、3：已销户 */
    private Integer accountStatus;

    /** 账户类型：1:活期 、2:定期 、3:通支 */
    private Integer accountType;

    /** 网银管理费：按照具体情况填写 */
    private Double onlineBankFee;

    /** 账户管理费：按照具体情况填写 */
    private Double accountMngFee;

    /** 是否有网银：1：是、2：否 */
    private Integer isOnlineBank;

    /** 回单形式：1:邮寄、2:自取、3:打印 */
    private Integer receiptForm;

    /** 预留印鉴记录 */
    private String reserveSeal;

    /** 申请人：具体申请人 */
    private String proposer;

    /** 银行联系方式：姓名、类型、电话、邮箱（长文本存放） */
    private String linkMan;

    /** 开户信息预留人 */
    private String openAccountObligate;

    /** 网银验证码预留人 */
    private String onlineBankObligate;

    /** 大额转款确定预留人 */
    private String bigAmounttransferObligate;

    /** 质押保证金 */
    private Double pledgefDeposits;

    /** 备注 */
    private String comments;

    /** 初始化金额 */
    private Double balance;

    /** 初始化金额 */
    public Double getBalance() {
        return balance;
    }

    /** 初始化金额 */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenBankAddress() {
        return openBankAddress;
    }

    public void setOpenBankAddress(String openBankAddress) {
        this.openBankAddress = openBankAddress;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getCooperationWay() {
        return cooperationWay;
    }

    public void setCooperationWay(Integer cooperationWay) {
        this.cooperationWay = cooperationWay;
    }

    public Integer getAccountNature() {
        return accountNature;
    }

    public void setAccountNature(Integer accountNature) {
        this.accountNature = accountNature;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Double getOnlineBankFee() {
        return onlineBankFee;
    }

    public void setOnlineBankFee(Double onlineBankFee) {
        this.onlineBankFee = onlineBankFee;
    }

    public Double getAccountMngFee() {
        return accountMngFee;
    }

    public void setAccountMngFee(Double accountMngFee) {
        this.accountMngFee = accountMngFee;
    }

    public Integer getIsOnlineBank() {
        return isOnlineBank;
    }

    public void setIsOnlineBank(Integer isOnlineBank) {
        this.isOnlineBank = isOnlineBank;
    }

    public Integer getReceiptForm() {
        return receiptForm;
    }

    public void setReceiptForm(Integer receiptForm) {
        this.receiptForm = receiptForm;
    }

    public String getReserveSeal() {
        return reserveSeal;
    }

    public void setReserveSeal(String reserveSeal) {
        this.reserveSeal = reserveSeal;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getOpenAccountObligate() {
        return openAccountObligate;
    }

    public void setOpenAccountObligate(String openAccountObligate) {
        this.openAccountObligate = openAccountObligate;
    }

    public String getOnlineBankObligate() {
        return onlineBankObligate;
    }

    public void setOnlineBankObligate(String onlineBankObligate) {
        this.onlineBankObligate = onlineBankObligate;
    }

    public String getBigAmounttransferObligate() {
        return bigAmounttransferObligate;
    }

    public void setBigAmounttransferObligate(String bigAmounttransferObligate) {
        this.bigAmounttransferObligate = bigAmounttransferObligate;
    }

    public Double getPledgefDeposits() {
        return pledgefDeposits;
    }

    public void setPledgefDeposits(Double pledgefDeposits) {
        this.pledgefDeposits = pledgefDeposits;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
