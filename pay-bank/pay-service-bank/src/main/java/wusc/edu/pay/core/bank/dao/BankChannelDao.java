package wusc.edu.pay.core.bank.dao;

import wusc.edu.pay.common.core.dao.BaseDao;
import wusc.edu.pay.facade.bank.entity.BankChannel;

import java.util.List;
import java.util.Map;

/**
 * @author System
 * @since 2013-11-07
 */
public interface BankChannelDao extends BaseDao<BankChannel> {

    /**
     * 根据银行渠道编号查找
     *
     * @param bankChannelCode
     * @return BankChannel
     */
    BankChannel getByBankChannelCode(String bankChannelCode);

    /**
     * 根据银行渠道编号模糊查找
     *
     * @param bankChannelCode
     * @param status
     * @return List
     */
    List<BankChannel> likeBy(String bankChannelCode, Integer status);

    /**
     * 删除银行渠道
     *
     * @param bankChannelCode
     */
    void deleteChannelByCode(String bankChannelCode);

    /***
     * 根据银行渠道名称查询银行渠道信息
     * @param channelName
     * @return
     */
    BankChannel getByBankChannelName(String channelName);

    /**
     * 根据银行协议ID获取银行渠道
     *
     * @param
     */
    BankChannel getByBankAgreementId(long bankAgreementId);

    /**
     * 条件查询
     */
    @Override
    List listBy(Map<String, Object> param);

    /**
     * 根据协议表中的业务类型和账户表中的账户性质查询出对应的渠道
     *
     * @param linkType
     * @param accountType
     */
    List<BankChannel> listChannalByAgreementBusTypeAndAccountType(int linkType, int accountType);

    boolean isUsableBankChannel(String bankChannelCode);

}