package com.chenshun.test.mapper;

import com.chenshun.test.db.DataSource;
import com.chenshun.test.db.DynamicDataSourceHolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User: chenshun131 <p />
 * Time: 18/3/15 21:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Repository
public interface AppMapper {

    @DataSource(value = DynamicDataSourceHolder.MASTER)
    List<Map<String, Object>> executeQuery(@Param("sql") String sql);

    @DataSource(value = DynamicDataSourceHolder.SLAVE)
    List<Map<String, Object>> executeQuery2(@Param("sql") String sql);

}
