package com.chenshun.transformer.dimension.key;

import org.apache.hadoop.io.WritableComparable;

/**
 * 维度信息类的基类<br/>
 * 所有输出到mysql数据库中的自定义MR任务的自定义key均需要实现自该抽象类
 * 
 * @author ibeifeng
 *
 */
public abstract class BaseDimension implements WritableComparable<BaseDimension> {
    // nothings
}
