package wusc.edu.pay.common.core.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import wusc.edu.pay.common.page.PageBean;
import wusc.edu.pay.common.page.PageParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param <T>
 * @描述: 数据访问层基础支撑接口
 * @作者: WuShuicheng
 * @创建时间: 2013-7-22,下午4:52:52
 * @版本: 1.0
 */
public interface BaseDao<T> {

    /**
     * 根据实体对象新增记录
     *
     * @param entity
     * @return id
     */
    long insert(T entity);

    /**
     * 批量保存对象
     *
     * @param list
     * @return id
     */
    long insert(List<T> list);

    /**
     * 更新实体对应的记录
     *
     * @param entity
     * @return
     */
    long update(T entity);

    /**
     * 批量更新对象
     *
     * @param list
     * @return int
     */
    long update(List<T> list);

    /**
     * 根据ID查找记录
     *
     * @param id
     * @return entity
     */
    T getById(long id);

    /**
     * 根据ID删除记录
     *
     * @param id
     * @return
     */
    long deleteById(long id);

    /**
     * 分页查询
     *
     * @param pageParam
     *         分页参数
     * @param paramMap
     *         业务条件查询参数
     * @return
     */
    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回集合
     */
    List<T> listBy(Map<String, Object> paramMap);

    List<Object> listBy(Map<String, Object> paramMap, String sqlId);

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回实体
     */
    T getBy(Map<String, Object> paramMap);

    Object getBy(Map<String, Object> paramMap, String sqlId);

    /**
     * 根据序列名称获取下一个值
     *
     * @return
     */
    String getSeqNextValue(String seqName);

    SqlSessionTemplate getSessionTemplate();

    SqlSession getSqlSession();

    /**
     * 通过条件查询指定数据的数量
     *
     * @param paramMap
     * @return
     */
    long listPageCount(Map<String, Object> paramMap);

    /**
     * 根据当前分页参数进行统计
     *
     * @param paramMap
     * @return
     */
    HashMap countByPageParam(Map<String, Object> paramMap);

    /**
     * 删除数据
     *
     * @param param
     * @return
     */
    long delete(Map<String, String> param);

}
