package com.chenshun.test.hadoop.mapreduce;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * User: mew <p />
 * Time: 18/2/27 13:47  <p />
 * Version: V1.0  <p />
 * Description: <p />
 */
public class WebPvMapReduce extends Configured implements Tool {

    // step 1 : Mapper Class
    public static class WebPvMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

        private IntWritable mapOutputValue = new IntWritable(1);

        private IntWritable mapOutputKey = new IntWritable();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
        }

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // line value
            String lineValue = value.toString();
            // split
            String[] values = lineValue.split("\t");
            if (30 > values.length) {
                // Counter
                context.getCounter("WEBPVMAPPER_COUNTERS", "LENGTH_LT_30_COUNTER").increment(1L);
                return;
            }

            // url
            String urlValue = values[1];
            if (StringUtils.isBlank(urlValue)) {
                // Counter
                context.getCounter("WEBPVMAPPER_COUNTERS", "URL_BLANK_COUNTER").increment(1L);
                return;
            }
            // provinceId
            String provinceIdValue = values[23];
            if (StringUtils.isBlank(provinceIdValue)) {
                // Counter
                context.getCounter("WEBPVMAPPER_COUNTERS", "PROVINCEID_BLANK_COUNTER").increment(1L);
                return;
            }
            Integer provinceId = Integer.MAX_VALUE;
            try {
                provinceId = Integer.valueOf(provinceIdValue);
            } catch (Exception e) {
                // Counter
                context.getCounter("WEBPVMAPPER_COUNTERS", "PROVINCEID_NOT_NUMBER_COUNTER").increment(1L);
                return;
            }
            // map output key
            mapOutputKey.set(provinceId);
            context.write(mapOutputKey, mapOutputValue);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
        }
    }

    // step 2: Reducer Class
    public static class WebPvReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

        private IntWritable outputValue = new IntWritable();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
        }

        @Override
        protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            // tmp
            int sum = 0;
            // iterator
            for (IntWritable value : values) {
                // total
                sum += value.get();
            }
            // set
            outputValue.set(sum);
            // output
            context.write(key, outputValue);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
        }
    }

    /**
     * @param args
     * @return
     * @throws Exception
     *         int run(String [] args) throws Exception;
     */
    // step 3: Driver
    public int run(String[] args) throws Exception {
        Configuration configuration = this.getConf();
        Job job = Job.getInstance(configuration, this.getClass().getSimpleName());
        job.setJarByClass(WebPvMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // Mapper
        job.setMapperClass(WebPvMapper.class);
        // TODO
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        // ================shuffle=====================
        // 1.partitioner
        // job.setPartitionerClass(cls);

        // 2.sort
        // job.setSortComparatorClass(cls);

        // 3.combiner
        job.setCombinerClass(WebPvReducer.class);

        // 4.group
        // job.setGroupingComparatorClass(cls);

        // ================shuffle=====================

        // Reducer
        job.setReducerClass(WebPvReducer.class);
        // TODO
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();

        // 传递两个参数，设置路径
        args = new String[]{
                "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/webpv/input",
                "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/webpv/output6"};

        // run job int status = new WCMapReduce().run(args);

        // System.exit(status);

        // run job
        int status = ToolRunner.run(configuration, new WebPvMapReduce(), args);

        // exit program
        System.exit(status);
    }

}
