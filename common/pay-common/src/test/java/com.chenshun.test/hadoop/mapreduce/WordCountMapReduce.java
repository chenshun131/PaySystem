package com.chenshun.test.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * User: mew <p />
 * Time: 18/2/27 13:49  <p />
 * Version: V1.0  <p />
 * Description: <p />
 */
public class WordCountMapReduce {

    // step 1 : Mapper Class
    public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        // 输出单词
        private Text mapOutputKey = new Text();

        // 出现一次就记做1次
        private IntWritable mapOutputValue = new IntWritable(1);

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // System.out.println("map-in-0-key: " + key.get() + " -- "
            // + "map-in-value: " + value.toString());

            // line value
            // 获取文件每一行的<key,value>
            String lineValue = value.toString();

            // split
            // 分割单词，以空格分割
            String[] strs = lineValue.split(" ");

            // iterator
            // 将数组里面的每一个单词拿出来，一个个组成<key,value>
            // 生成1
            for (String str : strs) {
                // 设置key
                // set map output key
                mapOutputKey.set(str);

                // output
                // 最终输出
                context.write(mapOutputKey, mapOutputValue);
                System.out.println("<" + mapOutputKey + "," + mapOutputValue + ">");
            }
        }

    }

    public static class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable outputValue = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            System.out.println("key =  " + key);
            // temp : sum
            // 定义一个临时变量
            int sum = 0;

            // iterator
            // 对于迭代器中的值进行迭代累加，最后sum加完以后就是统计的次数
            for (IntWritable value : values) {
                // total
                sum += value.get();
                System.out.println(value.get() + "");
            }
            System.out.println();
            // set output value
            outputValue.set(sum);
            // output
            context.write(key, outputValue);
        }
    }

    // step 2: Reducer Class
    public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

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

    // step 3: Driver
    public int run(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, this.getClass().getSimpleName());
        job.setJarByClass(WordCountMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // Mapper
        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // ================shuffle=====================
        // 1.partitioner
        // job.setPartitionerClass(cls);

        // 2.sort
        // job.setSortComparatorClass(cls);

        // 3.combiner
        //job.setCombinerClass(WordCountCombiner.class);

        // 4.group
        // job.setGroupingComparatorClass(cls);

        // ================shuffle=====================

        // Reducer
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        // 传递两个参数，设置路径
        args = new String[]{"hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/input",
                "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/output12"};

        // run job
        int status = new WordCountMapReduce().run(args);
        System.exit(status);
    }

}
