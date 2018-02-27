package com.chenshun.test.hadoop.mapreduce;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 需求：
 * 获取每日各省份UV
 * UV:独立访客数
 * 组合key：date_provice_guid
 * 表示：某天某个省份某个人无论访问该网站多少次，仅记做1次
 * 实际情况：
 * 需要分析一周的访问日志数据，进行统计每日各省份的UV，前端使用折线和曲线图展示
 */
public class WebUvMapReduce extends Configured implements Tool {

    // step 1 : Mapper Class
    public static class WebUvMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

        private Text mapOutputKey = new Text();

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
                return;
            }

            // guid ID
            //获取用户ID，并进行判断是否有值，若无值进行过滤
            String guidIDValue = values[5];
            if (StringUtils.isBlank(guidIDValue)) {
                return;
            }

            // track time
            //用户访问时间进行判断
            String trackTimeValue = values[17];
            if (StringUtils.isBlank(trackTimeValue)) {
                return;
            }
            String dateValue = trackTimeValue.substring(0, 10);

            // proviceID
            //获取各个省份信息进行判断
            String proviceIDValue = values[23];
            if (StringUtils.isBlank(proviceIDValue)) {
                return;
            }
            //=============优化：将省份不是数字的过滤掉===================
            try {
                Integer.valueOf(proviceIDValue);
            } catch (Exception e) {
                return;
            }
            //=============优化===========================================
            // date_provice_guid
            //拼接字符串：date_provice_guid
            mapOutputKey.set(dateValue + "\t" + proviceIDValue + "_" + guidIDValue);
            // output 输出
            context.write(mapOutputKey, NullWritable.get());
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
        }
    }

    // step 2: Reducer Class
    public static class WebUvReducer extends Reducer<Text, NullWritable, Text, IntWritable> {

        /**
         * 用户存储每天每个省份的UV数
         * Map<date_provice,count(guid)>
         * 比如：
         * 2015-08-28_29		117
         * 2015-08-28_20		632423
         * 2015-08-28_30		5445
         * 2015-08-28_31		76867
         */
        // 声明Map，便于全局使用
        private Map<String, Integer> dateMap;

        // output key
        private Text outputKey = new Text();

        // outout value
        private IntWritable outputValue = new IntWritable();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            // 初始化Map
            dateMap = new HashMap<>();
        }

        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            // date_provice ---> 2016-11-01\t29
            // 获取key，进行分割，得到2016-11-01\t29
            String date = key.toString().split("_")[0];
            // 进行判断，查看date_provice 是否存在Map集合中
            if (dateMap.containsKey(date)) {
                // 如果存在，再获取UV，再进行加一
                // 第一步：根据date_provice获取Map集合中的UV 值
                Integer previosUv = dateMap.get(date);

                // 第二步：UV值加一
                Integer uv = previosUv + 1;

                // 第三步：更新Map集合中date_provice对应新的UV值
                dateMap.put(date, uv);
            } else {
                // 如果不存在，说明第一次存储，UV值为1，格式<date_provice,1>
                // update
                dateMap.put(date, 1);
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            // 得到Map集合中的所有key，date_provice
            Set<String> dateSet = dateMap.keySet();

            // 迭代循环，依次获取每天每个省份的UV值
            for (String date : dateSet) {
                // 获取UV值
                Integer uv = dateMap.get(date);

                outputKey.set(date);
                outputValue.set(uv);

                // output
                context.write(outputKey, outputValue);
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
        job.setJarByClass(WebUvMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // Mapper
        job.setMapperClass(WebUvMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        // Reducer
        job.setReducerClass(WebUvReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        // 传递两个参数，设置路径
        args = new String[]{
                "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/webuv/input",
                "hdfs://hadoop-senior01.ibeifeng.com:8020/user/beifeng/webuv/output"};
        // run job int status = new WCMapReduce().run(args);

        // System.exit(status);

        // run job
        int status = ToolRunner.run(configuration, new WebUvMapReduce(), args);
        // exit program
        System.exit(status);
    }

}
