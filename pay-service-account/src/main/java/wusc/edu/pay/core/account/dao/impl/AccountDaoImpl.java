package wusc.edu.pay.core.account.dao.impl;

import org.springframework.stereotype.Repository;
import wusc.edu.pay.common.core.dao.BaseDaoImpl;
import wusc.edu.pay.core.account.dao.AccountDao;
import wusc.edu.pay.facade.account.entity.Account;

import java.util.HashMap;
import java.util.Map;


@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

    /**
     * 生成账户编号20位
     */
    @Override
    public String buildAccountNo(String accountType) {
        // 获取账户编号序列值，用于生成20位的账户编号
        String accountNoSeq = super.getSeqNextValue("ACCOUNT_NO_SEQ");
        // 构造账户编号
        return "8008" + accountType + accountNoSeq + "0101";
    }

    /**
     * 根據帳戶編號獲取帳戶信息
     *
     * @param accountNo
     * @return
     */
    @Override
    public Account getByAccountNo(String accountNo) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("accountNo", accountNo);
        return super.getBy(params);
    }

    /**
     * 获取账户实体
     *
     * @param userNo
     * @param isPessimist
     *         是否乐观锁
     * @return
     */
    @Override
    public Account getByUserNo_IsPessimist(String userNo, boolean isPessimist) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("userNo", userNo);
        params.put("isPessimist", isPessimist);
        return super.getBy(params);
    }

}
