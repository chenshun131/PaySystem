/**
 * wusc.edu.pay.core.common.biz;
 */
package wusc.edu.pay.common.core.biz;

import wusc.edu.pay.common.core.dao.BaseDao;
import wusc.edu.pay.common.entity.BaseEntity;
import wusc.edu.pay.common.page.PageBean;
import wusc.edu.pay.common.page.PageParam;

import java.util.List;
import java.util.Map;


/**
 * 基类Biz接口实现类
 *
 * @author Hill
 */
public abstract class BaseBizImpl<T extends BaseEntity> implements BaseBiz<T> {

    protected abstract BaseDao<T> getDao();

    @Override
    public long create(T entity) {
        return getDao().insert(entity);
    }

    @Override
    public long create(List<T> list) {
        return getDao().insert(list);
    }

    @Override
    public long update(T entity) {
        return getDao().update(entity);
    }

    @Override
    public long update(List<T> list) {
        return getDao().update(list);
    }

    @Override
    public T getById(long id) {
        return this.getDao().getById(id);
    }

    /**
     * 根据ID删除记录.
     *
     * @param id
     *         .
     * @return
     */
    @Override
    public long deleteById(long id) {
        return this.getDao().deleteById(id);
    }

    /**
     * 分页查询 .
     *
     * @param pageParam
     *         分页参数.
     * @param paramMap
     *         业务条件查询参数.
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
