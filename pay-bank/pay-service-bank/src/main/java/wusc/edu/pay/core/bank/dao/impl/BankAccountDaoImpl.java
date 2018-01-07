package wusc.edu.pay.core.bank.dao.impl;

import org.springframework.stereotype.Repository;
import wusc.edu.pay.common.core.dao.BaseDaoImpl;
import wusc.edu.pay.core.bank.dao.BankAccountDao;
import wusc.edu.pay.facade.bank.entity.BankAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: 银行账户信息表，数据访问层接口实现类
 * @作者: WuShuicheng
 * @创建时间: 2014-4-15, 下午2:24:17
 */
@Repository("bankAccountDao")
public class BankAccountDaoImpl extends BaseDaoImpl<BankAccount> implements BankAccountDao {

    /**
     * 根据银行账号模糊查找
     *
     * @param bankAccountKey
     * @param status
     * @return List
     */
    @Override
    public List<BankAccount> likeBy(String bankAccountKey, Integer status) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("bankAccountKey", bankAccountKey);
        params.put("status", status);
        return super.getSessionTemplate().selectList(getStatement("likeBy"), params);
    }

}