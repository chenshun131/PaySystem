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
import java.util.StringTokenizer;

/**
 * User: mew <p />
 * Time: 18/2/27 13:44  <p />
 * Version: V1.0  <p />
 * Description: <p />
 */
public class WCMapReduce extends Configured implements Tool {

    // step 1 : Mapper Class
    public static class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        // 输出单词
        private Text mapOutputKey = new Text();

        // 出现一次就记做1次
        private IntWritable mapOutputValue = new IntWritable(1);

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            System.out.println("map-in-0-key: " + key.get() + " -- map-in-value: " + value.toString());

            // line value
            // 获取文件每一行的<key,value>
            String lineValue = value.toString();

            // split
            // 分割单词，以空格分割
            // String[] strs = lineValue.split(" ");
            StringTokenizer stringTokenizer = new StringTokenizer(lineValue);
            while (stringTokenizer.hasMoreTokens()) {
                // set map output key
                mapOutputKey.set(stringTokenizer.nextToken());
                // output
                context.write(mapOutputKey, mapOutputValue);
            }
            // iterator
            // 将数组里面的每一个单词拿出来，一个个组成<key,value>
            // 生成1
            /**
             * for (String str : strs) { // 设置key // set map output key
             * mapOutputKey.set(str);
             *
             * // output // 最终输出 context.write(mapOutputKey, mapOutputValue); }
             */
        }
    }

    // step 2: Reducer Class
    public static class WCReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable outputValue = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            // temp : sum
            // 定义一个临时变量
            int sum = 0;

            // iterator
            // 对于迭代器中的值进行迭代累加，最后sum加完以后就是统计的次数
            for (IntWritable value : values) {
                // total
                sum += value.get();
            }

            // set output value
            outputValue.set(sum);

            // output
            context.write(key, outputValue);
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
        job.setJarByClass(WCMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // Mapper
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // ================shuffle=====================
        // 1.partitioner
        // job.setPartitionerClass(cls);

        // 2.sort
        // job.setSortComparatorClass(cls);

        // 3.combiner
        // job.setCombinerClass(WordCountCombiner.clazz);

        // 4.group
        // job.setGroupingComparatorClass(cls);

        // ================shuffle=====================

        // Reducer
        job.setReducerClass(WCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // reduce number
        // job.setNumReduceTasks(tasks);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("mapreduce.map.output.compress", "true");
        configuration.set("mapreduce.map.output.compress.codec", "org.apache.hadoop.io.compress.SnappyCodec");

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
        int status = ToolRunner.run(configuration, new WCMapReduce(), args);

        // exit program
        System.exit(status);
    }

}
