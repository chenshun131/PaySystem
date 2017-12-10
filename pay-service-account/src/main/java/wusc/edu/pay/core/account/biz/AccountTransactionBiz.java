package wusc.edu.pay.core.account.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wusc.edu.pay.common.constant.PublicStatus;
import wusc.edu.pay.common.utils.string.StringUtil;
import wusc.edu.pay.core.account.dao.AccountDao;
import wusc.edu.pay.core.account.dao.AccountFrozenHistoryDao;
import wusc.edu.pay.core.account.dao.AccountHistoryDao;
import wusc.edu.pay.facade.account.entity.Account;
import wusc.edu.pay.facade.account.entity.AccountFrozenHistory;
import wusc.edu.pay.facade.account.entity.AccountHistory;
import wusc.edu.pay.facade.account.enums.*;
import wusc.edu.pay.facade.account.exception.AccountBizException;
import wusc.edu.pay.facade.account.vo.AccountTransactionVo;

import java.util.ArrayList;
import java.util.List;

import static wusc.edu.pay.facade.account.enums.AccountFundDirectionEnum.*;


/**
 * 账户交易biz
 *
 * @author healy
 */
@Component("accountTransactionBiz")
@Transactional(rollbackFor = Exception.class)
public class AccountTransactionBiz {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountHistoryDao accountHistoryDao;

    @Autowired
    private AccountFrozenHistoryDao accountFrozenHistoryDao;

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionBiz.class);

    /**
     * 账户收/付款
     *
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    public void execute(AccountTransactionVo vo) {
        if (StringUtil.isBlank(vo.getUserNo())) {
            return;
        }

        logger.info("==>execute" + vo.getAccountFundDirection().toString());
        switch (vo.getAccountFundDirection()) {
            case ADD:
                this.credit(vo.getUserNo(), vo.getAmount(), vo.getRequestNo(), vo.getTradeType(), vo.getDesc(), vo.getRiskDay(), vo.getFee());
                break;
            case SUB:
                this.debit(vo.getUserNo(), vo.getAmount(), vo.getRequestNo(), vo.getTradeType(), vo.getDesc(), vo.getFee());
                break;
            case FROZEN:
                this.frozen(vo.getUserNo(), vo.getFrezonAmount(), vo.getRequestNo(), vo.getTradeType());
                break;
            case UNFROZEN:
                this.unFrozen(vo.getUserNo(), vo.getUnFrezonAmount(), vo.getRequestNo(), vo.getTradeType());
                break;
            default:
        }
        logger.info("==>execute<==");
    }

    /**
     * 账户收/付款
     *
     * @param voList
     */
    @Transactional(rollbackFor = Exception.class)
    public void execute(List<AccountTransactionVo> voList) {
        logger.info("==>executeList");
        for (AccountTransactionVo vo : voList) {
            this.execute(vo);
        }
        logger.info("==>executeList<==");
    }

    /**
     * 同一账户批量加款
     *
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchCreditForSameAccount(List<AccountTransactionVo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        logger.info("==>batchCreditForSameAccount");
        Account account = accountDao.getByUserNo_IsPessimist(list.get(0).getUserNo(), true);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", list.get(0).getUserNo()).print();
        }
        if (account.getStatus() == AccountStatusEnum.INACTIVE.getValue()
                || account.getStatus() == AccountStatusEnum.CANCELLED.getValue()
                || account.getStatus() == AccountStatusEnum.INACTIVE_FREEZE_CREDIT.getValue()) {
            throw AccountBizException.ACCOUNT_STATUS_IS_INACTIVE.newInstance("账户状态异常,用户编号{%s},账户状态{%s}", list.get(0).getUserNo(), account.getStatus()).print();
        }
        int isAllowSett = PublicStatus.ACTIVE;

        // 如果accountType是会员，isAllowSett置false
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        List<AccountHistory> listHistory = new ArrayList<>();
        for (AccountTransactionVo vo : list) {
            if (!vo.getUserNo().equals(account.getUserNo())) {
                throw AccountBizException.BATCH_CREDIT_FOR_SAME_ACCOUNT_ERROR.print();
            }
            // 加款
            account.credit(vo.getAmount());

            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setAllowSett(isAllowSett);
            accountHistory.setAmount(vo.getAmount());
            accountHistory.setFee(vo.getFee());
            accountHistory.setBalance(account.getAvailableBalance());
            accountHistory.setRequestNo(vo.getRequestNo());
            accountHistory.setCompleteSett(PublicStatus.INACTIVE);
            accountHistory.setRemark(vo.getTradeType().getDesc());
            accountHistory.setFundDirection(ADD.getValue());
            accountHistory.setAccountNo(account.getAccountNo());
            accountHistory.setTrxType(vo.getTradeType().getValue());
            accountHistory.setRiskDay(vo.getRiskDay());
            listHistory.add(accountHistory);
        }

        accountDao.update(account);
        accountHistoryDao.insert(listHistory);
        logger.info("==>batchCreditForSameAccount<==");
    }

    /**
     * 加款
     *
     * @param userNo
     * @param amount
     * @param requestNo
     * @param tradeType
     * @param remark
     * @param riskDay
     * @param fee
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String credit(String userNo, double amount, String requestNo, AccountTradeTypeEnum tradeType, String remark, Integer riskDay, double fee) {
        logger.info("==>credit");
        logger.info(String.format("==>userNo:%s, amount:%s, requestNo:%s, tradeType:%s, remark:%s", userNo, amount, requestNo, tradeType.name(), remark));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, true);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }
        // 加款
        account.credit(amount);

        int isAllowSett = PublicStatus.ACTIVE;

        // 如果accountType是会员，isAllowSett置false
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        AccountHistory accountHistoryEntity = new AccountHistory();
        accountHistoryEntity.setAllowSett(isAllowSett);
        accountHistoryEntity.setAmount(amount);
        accountHistoryEntity.setBalance(account.getAvailableBalance());
        accountHistoryEntity.setRequestNo(requestNo);
        accountHistoryEntity.setCompleteSett(PublicStatus.INACTIVE);
        accountHistoryEntity.setRemark(remark);
        accountHistoryEntity.setFee(fee);
        accountHistoryEntity.setFundDirection(ADD.getValue());
        accountHistoryEntity.setAccountNo(account.getAccountNo());
        accountHistoryEntity.setTrxType(tradeType.getValue());
        accountHistoryEntity.setRiskDay(riskDay);

        accountHistoryDao.insert(accountHistoryEntity);
        accountDao.update(account);
        logger.info("==>credit<==");
        return account.getAccountTitleNo();
    }

    /**
     * 减款
     *
     * @param userNo
     * @param amount
     * @param requestNo
     * @param tradeType
     * @param remark
     * @param fee
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String debit(String userNo, double amount, String requestNo, AccountTradeTypeEnum tradeType, String remark, double fee) {
        logger.info("==>debit");
        logger.info(String.format("==>userNo:%s, amount:%s, requestNo:%s, tradeType:%s, remark:%s", userNo, amount, requestNo, tradeType.name(), remark));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, true);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }
        // 减款
        account.debit(amount);
        // 如果accountType是会员，isAllowSett置false
        int isAllowSett = PublicStatus.ACTIVE;
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            isAllowSett = PublicStatus.INACTIVE;
        }
        // 结算，提现交易类型不允许结算 by chenjianhua
        if (tradeType.equals(AccountTradeTypeEnum.SETTLEMENT) || tradeType.equals(AccountTradeTypeEnum.ATM)) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        AccountHistory accountHistoryEntity = new AccountHistory();
        accountHistoryEntity.setAllowSett(isAllowSett);
        accountHistoryEntity.setAmount(amount);
        accountHistoryEntity.setFee(fee);
        accountHistoryEntity.setBalance(account.getAvailableBalance());
        accountHistoryEntity.setRequestNo(requestNo);
        accountHistoryEntity.setCompleteSett(PublicStatus.INACTIVE);
        accountHistoryEntity.setRemark(remark);
        accountHistoryEntity.setFundDirection(SUB.getValue());
        accountHistoryEntity.setAccountNo(account.getAccountNo());
        accountHistoryEntity.setTrxType(tradeType.getValue());
        accountHistoryDao.insert(accountHistoryEntity);
        accountDao.update(account);
        logger.info("==>debit<==");
        return account.getAccountTitleNo();
    }

    /**
     * 资金冻结.
     *
     * @param userNo
     *         用户编号.
     * @param frozenAmount
     *         冻结金额.
     * @param requestNo
     *         请求号.
     * @param tradeType
     *         账户交易类型.
     */
    @Transactional(rollbackFor = Exception.class)
    public void frozen(String userNo, double frozenAmount, String requestNo, AccountTradeTypeEnum tradeType) {

        logger.info("==>frozen");
        logger.info(String.format("==>userNo:%s, frozenAmount:%s, requestNo:%s, tradeType:%s", userNo, frozenAmount, requestNo, tradeType.name()));

        // if (AmountUtil.greaterThanOrEqualTo(0, frozenAmount)) {
        // throw AccountBizException.ACCOUNT_AMOUNT_ERROR.print();
        // }

        Account account = accountDao.getByUserNo_IsPessimist(userNo, true);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        // 资金冻结
        account.frozen(frozenAmount);

        // 如果accountType是会员，isAllowSett置false
        int isAllowSett = PublicStatus.ACTIVE;
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        // 结算，提现交易类型不允许结算 by chenjianhua
        if (tradeType.equals(AccountTradeTypeEnum.SETTLEMENT) || tradeType.equals(AccountTradeTypeEnum.ATM)) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        AccountFrozenHistory accountFrozenHistory = new AccountFrozenHistory();
        accountFrozenHistory.setAmount(frozenAmount);
        accountFrozenHistory.setCurrentAmount(account.getUnBalance());
        accountFrozenHistory.setRequestNo(requestNo);
        accountFrozenHistory.setRemark(tradeType.getDesc() + "资金冻结");
        accountFrozenHistory.setAccountFrozenHistoryType(AccountFrozenHistoryTypeEnum.FROZEN.getValue());
        accountFrozenHistory.setAccountNo(account.getAccountNo());
        accountFrozenHistory.setTrxType(tradeType.getValue());

        AccountHistory accountHistoryEntity = new AccountHistory();
        accountHistoryEntity.setAllowSett(isAllowSett);
        accountHistoryEntity.setAmount(frozenAmount);
        accountHistoryEntity.setBalance(account.getAvailableBalance());
        accountHistoryEntity.setRequestNo(requestNo);
        accountHistoryEntity.setCompleteSett(PublicStatus.INACTIVE);
        accountHistoryEntity.setRemark(tradeType.getDesc() + "资金冻结");
        accountHistoryEntity.setFundDirection(SUB.getValue());
        accountHistoryEntity.setAccountNo(account.getAccountNo());
        accountHistoryEntity.setTrxType(tradeType.getValue());

        accountDao.update(account);
        accountHistoryDao.insert(accountHistoryEntity);
        accountFrozenHistoryDao.insert(accountFrozenHistory);

        logger.info("==>frozen<==");
    }

    /**
     * 资金解冻.
     *
     * @param userNo
     * @param unFrozenAmount
     * @param requestNo
     * @param tradeType
     */
    @Transactional(rollbackFor = Exception.class)
    public void unFrozen(String userNo, double unFrozenAmount, String requestNo, AccountTradeTypeEnum tradeType) {

        logger.info("==>unFrozen");
        logger.info(String.format("==>userNo:%s, unFrozenAmount:%s, requestNo:%s, tradeType:%s", userNo, unFrozenAmount, requestNo, tradeType.name()));

        // if (AmountUtil.greaterThanOrEqualTo(0, unFrozenAmount)) {
        // throw AccountBizException.ACCOUNT_AMOUNT_ERROR.print();
        // }

        Account account = accountDao.getByUserNo_IsPessimist(userNo, true);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        // 资金解冻
        account.unFrozen(unFrozenAmount);

        // 如果accountType是会员，isAllowSett置false
        int isAllowSett = PublicStatus.ACTIVE;
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        // 结算，提现交易类型不允许结算 by chenjianhua
        if (tradeType.equals(AccountTradeTypeEnum.SETTLEMENT) || tradeType.equals(AccountTradeTypeEnum.ATM)) {
            isAllowSett = PublicStatus.INACTIVE;
        }

        AccountFrozenHistory accountFrozenHistory = new AccountFrozenHistory();
        accountFrozenHistory.setAmount(unFrozenAmount);
        accountFrozenHistory.setCurrentAmount(account.getUnBalance());
        accountFrozenHistory.setRequestNo(requestNo);
        accountFrozenHistory.setRemark(tradeType.getDesc() + "资金解冻");
        accountFrozenHistory.setAccountFrozenHistoryType(AccountFrozenHistoryTypeEnum.UNFROZEN.getValue());
        accountFrozenHistory.setAccountNo(account.getAccountNo());
        accountFrozenHistory.setTrxType(tradeType.getValue());

        AccountHistory accountHistoryEntity = new AccountHistory();
        accountHistoryEntity.setAllowSett(isAllowSett);
        accountHistoryEntity.setAmount(unFrozenAmount);
        accountHistoryEntity.setBalance(account.getAvailableBalance());
        accountHistoryEntity.setRequestNo(requestNo);
        accountHistoryEntity.setCompleteSett(PublicStatus.INACTIVE);
        accountHistoryEntity.setRemark(tradeType.getDesc() + "资金解冻");
        accountHistoryEntity.setFee(0D);
        accountHistoryEntity.setFundDirection(ADD.getValue());
        accountHistoryEntity.setAccountNo(account.getAccountNo());
        accountHistoryEntity.setTrxType(tradeType.getValue());

        accountDao.update(account);
        accountFrozenHistoryDao.insert(accountFrozenHistory);
        accountHistoryDao.insert(accountHistoryEntity);

        logger.info("==>unFrozen<==");
    }

    /**
     * 资金解冻并减款.
     *
     * @param userNo
     * @param amount
     * @param requestNo
     * @param tradeType
     * @param fee
     */
    @Transactional(rollbackFor = Exception.class)
    public void unfrozen_debit(String userNo, double amount, String requestNo, AccountTradeTypeEnum tradeType, double fee) {

        this.unFrozen(userNo, amount, requestNo, tradeType);
        this.debit(userNo, amount, requestNo, tradeType, tradeType.getDesc(), fee);

    }

}
