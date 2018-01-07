package wusc.edu.pay.core.cost.dao;

import wusc.edu.pay.common.core.dao.BaseDao;
import wusc.edu.pay.facade.cost.entity.CalDimension;

import java.util.List;

/**
 * 计费维度DAO
 */
public interface CalDimensionDao extends BaseDao<CalDimension> {

    /**
     * 根据银行渠道编号获取计费维度列表
     *
     * @param calInterface
     *         银行渠道编码
     * @return
     */
    List<CalDimension> getCalDimensionByCalInterface(String calInterface);

}
