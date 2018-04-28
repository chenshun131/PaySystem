package com.chenshun.transformer.dimension.value;

import com.chenshun.transformer.common.KpiType;
import org.apache.hadoop.io.Writable;

/**
 * 自定义顶级的输出value父类
 *
 * @author ibeifeng
 */
public abstract class BaseStatsValueWritable implements Writable {

    /**
     * 获取当前value对应的kpi值， 由kpi来确定具体数据的输出方式
     *
     * @return
     */
    public abstract KpiType getKpi();
}
