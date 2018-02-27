package com.chenshun.test.hadoop.mapreduce;

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
 * Time: 18/2/27 13:44  <p />
 * Version: V1.0  <p />
 * Description: <p />
 */
public class ModuelWCMapReduce extends Configured implements Tool {

    // step 1 : Mapper Class
    public static class ModuelMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
        }

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // TODO
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
        }
    }

    // step 2: Reducer Class
    public static class ModuelReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
        }

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            // TODO
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
        job.setJarByClass(ModuelWCMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // Mapper
        job.setMapperClass(ModuelMapper.class);
        // TODO
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Reducer
        job.setReducerClass(ModuelReducer.class);
        // TODO
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        /**
         * // 传递两个参数，设置路径 args = new String[] {
         * "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/input",
         * "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/output3" };
         *
         * // run job int status = new WCMapReduce().run(args);
         *
         * System.exit(status);
         */
        // run job
        int status = ToolRunner.run(configuration, new ModuelWCMapReduce(), args);

        // exit program
        System.exit(status);
    }

}
