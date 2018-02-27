package com.chenshun.test.hadoop.mapreduce.order;

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
 * User: chenshun131 <p />
 * Time: 16/11/13 8:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SortMapReduce extends Configured implements Tool {

    // step 1 : Mapper Class
    public static class SortMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        private Text mapOutputKey = new Text();

        private IntWritable mapOutputValue = new IntWritable();

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // line value
            String lineValue = value.toString();
            // split
            String[] strs = lineValue.split(",");
            // invalidate
            if (2 != strs.length) {
                return;
            }
            // set map output key
            mapOutputKey.set(strs[0]);
            mapOutputValue.set(Integer.valueOf(strs[1]));
            // output
            context.write(mapOutputKey, mapOutputValue);
        }
    }

    // step 2: Reducer Class
    public static class SortReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            // iterator
            for (IntWritable value : values) {
                context.write(key, value);
            }
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
        job.setJarByClass(SortMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // Mapper
        job.setMapperClass(SortMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // ================shuffle=====================
        // 1.partitioner
        // job.setPartitionerClass(cls);

        // 2.sort
        // job.setSortComparatorClass(cls);

        // 3.combiner
        // job.setCombinerClass(WordCountCombiner.class);

        // 4.group
        // job.setGroupingComparatorClass(cls);

        // ================shuffle=====================

        // Reducer
        job.setReducerClass(SortReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // reduce number
        job.setNumReduceTasks(5);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        args = new String[]{
                "hdfs://hadoop-senior01:8020/user/chenshun/sort/input",
                "hdfs://hadoop-senior01:8020/user/chenshun/sort/output3"};
        int status = ToolRunner.run(configuration, new SortMapReduce(), args);
        System.exit(status);
    }

}
