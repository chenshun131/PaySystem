package wusc.edu.pay.common.core.service;

import wusc.edu.pay.common.core.dao.BaseDao;
import wusc.edu.pay.common.entity.BaseEntity;
import wusc.edu.pay.common.page.PageBean;
import wusc.edu.pay.common.page.PageParam;

import java.util.List;
import java.util.Map;


/**
 * Service 基类实现
 *
 * @param <T>
 * @author Hill
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected abstract BaseDao<T> getDao();

    @Override
    public T getById(long id) {
        return this.getDao().getById(id);
    }

    /**
     * 分页查询
     *
     * @param pageParam
     *         分页参数
     * @param paramMap
     *         业务条件查询参数
     * @return
     */
    @Override
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return this.getDao().listPage(pageParam, paramMap);
    }

    @Override
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
        return this.getDao().listPage(pageParam, paramMap, sqlId);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回集合
     */
    @Override
    public List<T> listBy(Map<String, Object> paramMap) {
        return this.getDao().listBy(paramMap);
    }

    @Override
    public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
        return this.getDao().listBy(paramMap, sqlId);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回实体
     */
    @Override
    public T getBy(Map<String, Object> paramMap) {
        return this.getDao().getBy(paramMap);
    }

    @Override
    public Object getBy(Map<String, Object> paramMap, String sqlId) {
        return this.getDao().getBy(paramMap, sqlId);
    }

    /**
     * 根据序列名称获取下一个值
     *
     * @return
     */
    @Override
    public String getSeqNextValue(String seqName) {
        return this.getDao().getSeqNextValue(seqName);
    }

}
