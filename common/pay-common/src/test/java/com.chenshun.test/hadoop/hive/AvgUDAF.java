package com.chenshun.test.hadoop.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * 自定义UDAF, 实现求取平均值
 * <p>
 * 平均值如何计算：
 * select deptno, avg(sal) from emp group by deptno ;
 * 缓冲数据：
 * sal_total = sal + sal + sal + sal + sal
 * sal_count = 1   + 1   +  1  +  1  + 1
 * 输出
 * sal_total / sal_count
 */
public class AvgUDAF extends AbstractGenericUDAFResolver {

    /**
     * -1, 检查参数长度和类型
     * <p>
     * -2, GenericUDAFEvaluator
     * 真正的进行业务逻辑处理对象
     */
    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] info) throws SemanticException {
        // 判断是否只有一个参数
        if (info.length != 1) {
            throw new UDFArgumentTypeException(info.length - 1, "Exactly one argument is expected.");
        }

        // 判断输入的数据类型是否为基本数据类型
        if (info[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentTypeException(0,
                    "Only primitive type arguments are accepted bug " + info[0].getTypeName() + " is passed.");
        }

        return new GenericUDAFEvaluatorAvg();
    }

    /**
     * 真正处理数据
     */
    public static class GenericUDAFEvaluatorAvg extends GenericUDAFEvaluator {

        /**
         * Get a new aggregation object.
         */
        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            return null;
        }

        /**
         * Reset the aggregation. This is useful if we want to reuse the same
         * aggregation.
         *
         * @param agg
         */
        @Override
        public void reset(AggregationBuffer agg) throws HiveException {
        }

        /**
         * Iterate through original data.
         *
         * @param agg
         * @param parameters
         */
        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
        }

        /**
         * Get partial aggregation result.
         *
         * @param agg
         * @return partial aggregation result.
         */
        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            return null;
        }

        /**
         * Merge with partial aggregation result. NOTE: null might be passed in case
         * there is no input data.
         *
         * @param agg
         * @param partial
         */
        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {
        }

        /**
         * Get final aggregation result.
         *
         * @param agg
         * @return final aggregation result.
         */
        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
            return null;
        }
    }

}
