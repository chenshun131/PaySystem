package com.chenshun.test.hadoop.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * User: chenshun131 <p />
 * Time: 16/12/14 21:41  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HMr extends Configured implements Tool {

    /**
     * map
     * <p>
     * ImmutableBytesWritable, Result, KEYOUT, VALUEOUT
     */
    public static class rdMapper extends TableMapper<ImmutableBytesWritable, Put> {

        @Override
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
            Put put = new Put(key.get());
            for (Cell cell : value.rawCells()) {
                if ("info".equals(Bytes.toString(CellUtil.cloneFamily(cell)))) {
                    if ("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                        put.add(cell);
                    }
                }
            }
            context.write(key, put);
        }
    }

    /**
     * reduce
     * KEYIN, VALUEIN, KEYOUT, Mutation
     */
    public static class wrReduce extends TableReducer<ImmutableBytesWritable, Put, ImmutableBytesWritable> {

        @Override
        protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
            for (Put put : values) {
//				List<Cell>  cells = put.get("info", "name");
//				new put()
//				put1.add("base",CellUtil.cloneQualifier(cell),CellUtil.cloneValue(cell));
                context.write(key, put);
            }
        }
    }

    /**
     * 当前的mapreduce的写法，只适用于我们的需求
     *
     * 添加的cell是否能插入我们的目标表
     *
     * 假设t5表的列簇不叫info，叫base
     *
     * 取出源表中cell的rowkey，col，value，组成新的cell，放到新的put实例中去
     */


    /**
     * driver
     */
    public int run(String[] args) throws Exception {
        Configuration conf = super.getConf();
        Job job = Job.getInstance(conf, "mr-hbase");
        job.setJarByClass(HMr.class);

        /**
         * map and reduce - inuput and output
         */

        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob(
                "student:stu_info",      // input table
                scan,             // Scan instance to control CF and attribute selection
                rdMapper.class,   // mapper class
                ImmutableBytesWritable.class,              // mapper output key
                Put.class,             // mapper output value
                job);
        TableMapReduceUtil.initTableReducerJob(
                "t5",      // output table
                wrReduce.class,             // reducer class
                job);
        job.setNumReduceTasks(1);
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        // create a hbase conf instance
        Configuration conf = HBaseConfiguration.create();
        // run the job
        int status = ToolRunner.run(conf, new HMr(), args);
        // exit
        System.exit(status);
    }

}
