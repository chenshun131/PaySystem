package wusc.edu.pay.core.account.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wusc.edu.pay.core.account.dao.AccountDao;
import wusc.edu.pay.core.account.dao.AccountHistoryDao;
import wusc.edu.pay.facade.account.entity.Account;
import wusc.edu.pay.facade.account.enums.AccountFundDirectionEnum;
import wusc.edu.pay.facade.account.enums.AccountTradeTypeEnum;
import wusc.edu.pay.facade.account.enums.AccountTypeEnum;
import wusc.edu.pay.facade.account.exception.AccountBizException;
import wusc.edu.pay.facade.account.vo.AccountTransactionVo;

import java.util.ArrayList;
import java.util.List;


@Component("accountSettBiz")
public class AccountSettBiz {

    private static final Logger logger = LoggerFactory.getLogger(AccountSettBiz.class);

    @Autowired
    private AccountHistoryDao accountHistoryDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountTransactionBiz accountTransactionBiz;

    /**
     * 结算创建_T+0
     *
     * @param userNo
     * @param settAmount
     * @param requestNo
     *         结算请求
     * @param trxNo
     *         账户历史交易请求
     */
    @Transactional(rollbackFor = Exception.class)
    public void settCreateT0(String userNo, Double settAmount, String requestNo, String trxNo) {
        logger.info("==>settSuccess");
        logger.info(String.format("==>userNo:%s, requestNo:%s", userNo, requestNo));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, false);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        List<AccountTransactionVo> voList = new ArrayList<>();

        // 账户资金冻结
        AccountTransactionVo vo = new AccountTransactionVo();
        vo.setAccountFundDirection(AccountFundDirectionEnum.FROZEN);
        vo.setUserNo(userNo);
        vo.setFrezonAmount(settAmount);
        vo.setRequestNo(requestNo);
        vo.setTradeType(AccountTradeTypeEnum.SETTLEMENT);
        vo.setDesc("资金冻结");
        voList.add(vo);

        accountTransactionBiz.execute(voList);
        accountHistoryDao.updateCompleteSettTo100_t0(account.getAccountNo(), trxNo);
        logger.info("==>settSuccess<==");
    }

    /**
     * 结算成功
     *
     * @param userNo
     * @param settAmount
     * @param requestNo
     */
    @Transactional(rollbackFor = Exception.class)
    public void settSuccess(String userNo, Double settAmount, String requestNo) {
        logger.info("==>settSuccess");
        logger.info(String.format("==>userNo:%s, settAmount:%s, requestNo:%s, ", userNo, settAmount, requestNo));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, false);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        List<AccountTransactionVo> voList = new ArrayList<>();

        AccountTradeTypeEnum accountTradeType = AccountTradeTypeEnum.SETTLEMENT;
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            accountTradeType = AccountTradeTypeEnum.ATM;
        }

        // 虚拟账户解冻+减款
        AccountTransactionVo vo = new AccountTransactionVo();
        vo.setAccountFundDirection(AccountFundDirectionEnum.UNFROZEN);
        vo.setUserNo(userNo);
        vo.setUnFrezonAmount(settAmount);
        vo.setRequestNo(requestNo);
        vo.setTradeType(accountTradeType);
        vo.setDesc("资金解冻");
        voList.add(vo);

        vo = new AccountTransactionVo();
        vo.setAccountFundDirection(AccountFundDirectionEnum.SUB);
        vo.setUserNo(userNo);
        vo.setAmount(settAmount);
        vo.setRequestNo(requestNo);
        vo.setTradeType(accountTradeType);
        vo.setDesc(accountTradeType.getDesc());
        voList.add(vo);

        accountTransactionBiz.execute(voList);
        logger.info("==>settSuccess<==");
    }

    /**
     * 结算失败
     *
     * @param userNo
     * @param settAmount
     * @param requestNo
     */
    @Transactional(rollbackFor = Exception.class)
    public void settFail(String userNo, Double settAmount, String requestNo) {
        logger.info("==>settFail");
        logger.info(String.format("==>userNo:%s, settAmount:%s, requestNo:%s, ", userNo, settAmount, requestNo));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, false);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        List<AccountTransactionVo> voList = new ArrayList<AccountTransactionVo>();

        AccountTradeTypeEnum accountTradeType = AccountTradeTypeEnum.SETTLEMENT;
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            accountTradeType = AccountTradeTypeEnum.ATM;
        }

        // 虚拟账户解冻
        AccountTransactionVo vo = new AccountTransactionVo();
        vo.setAccountFundDirection(AccountFundDirectionEnum.UNFROZEN);
        vo.setUserNo(userNo);
        vo.setUnFrezonAmount(settAmount);
        vo.setRequestNo(requestNo);
        vo.setTradeType(accountTradeType);
        vo.setDesc("资金解冻");
        voList.add(vo);

        accountTransactionBiz.execute(voList);
        logger.info("==>settFail<==");
    }

    /**
     * 结算汇总成功
     *
     * @param userNo
     * @param statDate
     * @param riskDay
     */
    @Transactional(rollbackFor = Exception.class)
    public void settCollectSuccess(String userNo, String statDate, Integer riskDay) {
        logger.info("==>settCollectSuccess");
        logger.info(String.format("==>userNo:%s, statDate:%s, riskDay:%s", userNo, statDate, riskDay));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, false);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        accountHistoryDao.updateCompleteSettTo100(account.getAccountNo(), statDate, riskDay);
        logger.info("==>settCollectSuccess<==");
    }

    /**
     * 结算创建
     *
     * @param userNo
     * @param settAmount
     * @param requestNo
     * @param lastId
     */
    @Transactional(rollbackFor = Exception.class)
    public void settCreate(String userNo, Double settAmount, String requestNo, Long lastId) {
        logger.info("==>settSuccess");
        logger.info(String.format("==>userNo:%s, settAmount:%s, requestNo:%s, lastId:%s", userNo, settAmount, requestNo, lastId));

        Account account = accountDao.getByUserNo_IsPessimist(userNo, false);
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
        }

        AccountTradeTypeEnum accountTradeType = AccountTradeTypeEnum.SETTLEMENT;
        if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
            accountTradeType = AccountTradeTypeEnum.ATM;
        }

        // 账户资金冻结
        AccountTransactionVo vo = new AccountTransactionVo();
        vo.setAccountFundDirection(AccountFundDirectionEnum.FROZEN);
        vo.setUserNo(userNo);
        vo.setFrezonAmount(settAmount);
        vo.setRequestNo(requestNo);
        vo.setTradeType(accountTradeType);
        vo.setDesc("资金冻结");

        accountTransactionBiz.execute(vo);
        accountHistoryDao.updateCompleteSettTo100LastId(account.getAccountNo(), lastId);
        logger.info("==>settSuccess<==");
    }

}
