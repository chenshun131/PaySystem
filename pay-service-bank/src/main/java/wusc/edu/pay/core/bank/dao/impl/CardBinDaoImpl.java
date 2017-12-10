package wusc.edu.pay.core.bank.dao.impl;

import org.springframework.stereotype.Repository;
import wusc.edu.pay.common.core.dao.BaseDaoImpl;
import wusc.edu.pay.core.bank.dao.CardBinDao;
import wusc.edu.pay.facade.bank.entity.CardBin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huqian
 * @since 2013-11-07
 */
@Repository(value = "cardBinDao")
public class CardBinDaoImpl extends BaseDaoImpl<CardBin> implements CardBinDao {

    @Override
    public CardBin getByCardBin(String cardBin, Integer status) {
        Map<String, Object> params = new HashMap<>();
        params.put("cardBin", cardBin);
        params.put("status", status);
        return super.getBy(params);
    }

}