package com.chenshun.test.hadoop.hive.udf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * 自定义UDAF, 实现求取平均数的功能
 */
public class AvgUDAF extends AbstractGenericUDAFResolver {

    public static final Log LOG = LogFactory.getLog(AvgUDAF.class.getName());

    /**
     * -1, 检查参数长度和类型
     * -2, 根据参数返回对应的实际处理对象
     */
    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] info) throws SemanticException {
        /**
         * ype-checking goes here!
         */
        // 判断是否只有一个参数
        if (info.length != 1) {
            throw new UDFArgumentTypeException(info.length - 1, "Exactly one argument is expected.");
        }
        // 判断输入列的数据类型是否为基本类型
        if (info[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentTypeException(0, "Only primitive type arguments are accepted but " + info[0].getTypeName() + " is passed.");
        }
        // return  UDAFEvaluator
        return new GenericUDAFEvaluatorAvg();
    }

    public static class GenericUDAFEvaluatorAvg extends GenericUDAFEvaluator {

        // 定义输入数据类型
        public PrimitiveObjectInspector inspector;

        /**
         * 实现AggregationBuffer 类
         */
        public static class AvgAggregationBuffer implements AggregationBuffer {

            // 统计个数
            private long count;

            // 统计累加和
            private double sum;
        }

        /**
         * This function should be overriden in every sub class
         * And the sub class should call super.init(m, parameters) to get mode set.
         */
        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            // 输入数据的个数判断
            if (parameters.length != 1) {
                throw new UDFArgumentException("参数异常");
            }
            // invoke super init() method
            super.init(m, parameters);

            // 判断
            if (inspector == null) {
                this.inspector = (PrimitiveObjectInspector) parameters[0];
            }

            // 注意返回的类型要与最终sum的类型一致
            return PrimitiveObjectInspectorFactory.writableDoubleObjectInspector;
        }

        /**
         * Get a new aggregation object.
         */
        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            // Get Instance
            AvgAggregationBuffer avgBuffer = new AvgAggregationBuffer();
            // reset
            reset(avgBuffer);
            // return
            return avgBuffer;
        }

        /**
         * Reset the aggregation. This is useful if we want to reuse the same
         * aggregation.
         *
         * @param agg
         */
        @Override
        public void reset(AggregationBuffer agg) throws HiveException {
            // 强制类型转换
            AvgAggregationBuffer avgBuffer = (AvgAggregationBuffer) agg;
            // 初始化
            avgBuffer.count = 0;
            avgBuffer.sum = 0.0;
        }

        /**
         * Iterate through original data.
         *
         * @param agg
         * @param parameters
         */
        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
            if (parameters.length != 1) {
                throw new UDFArgumentException("参数错误");
            }
            // 调用merge()
            this.merge(agg, parameters[0]);
        }

        /**
         * Get partial aggregation result.
         *
         * @param agg
         * @return partial aggregation result.
         */
        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            return this.terminate(agg);
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
            AvgAggregationBuffer avgBuffer = (AvgAggregationBuffer) agg;
            if (partial != null) {
                // 个数 加1
                avgBuffer.count += 1;
                // 值累加
                avgBuffer.sum += PrimitiveObjectInspectorUtils.getDouble(partial, inspector);
            }
        }

        /**
         * Get final aggregation result.
         *
         * @param agg
         * @return final aggregation result.
         */
        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
            AvgAggregationBuffer avgBuffer = (AvgAggregationBuffer) agg;
            return new DoubleWritable(avgBuffer.sum / avgBuffer.count);
        }
    }

}
