package com.chenshun.transformer.service.converter;

import com.ibeifeng.transformer.dimension.key.BaseDimension;

import java.io.Closeable;
import java.io.IOException;

/**
 * 提供专门操作dimension表的接口<br/>
 * 根据dimension表的维度值获取维度id
 *
 * @author ibf
 */
public interface IDimensionConverter extends Closeable
{
    /**
     * 根据dimension的value值获取id<br/>
     * 如果数据库中有，那么直接返回。如果没有，那么进行插入后返回新的id值
     *
     * @param dimension
     * @return
     * @throws IOException
     */
    public int getDimensionIdByValue(BaseDimension dimension) throws IOException;
}
