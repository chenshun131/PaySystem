package com.chenshun.test.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * User: chenshun131 <p />
 * Time: 18/3/15 21:20  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 从自定义的位置获取数据源标识
        return DynamicDataSourceHolder.getDataSource();
    }

}
