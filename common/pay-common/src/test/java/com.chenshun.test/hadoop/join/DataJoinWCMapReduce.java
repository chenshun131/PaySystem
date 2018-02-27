package com.chenshun.test.hadoop.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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
import java.util.ArrayList;
import java.util.List;

// MapReduce Model,it had 
public class DataJoinWCMapReduce extends Configured implements Tool {

    // step1: Mapper Class
    public static class DataJoinMapper extends Mapper<LongWritable, Text, LongWritable, DataJoinWritable> {

        private LongWritable mapOutputKey = new LongWritable();// map output key

        private DataJoinWritable mapOutputValue = new DataJoinWritable();// map output value

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
        }

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // line value
            String lineValue = value.toString();
            // split
            String[] vals = lineValue.split(",");
            int length = vals.length;
            if ((length != 3) && (length != 4)) {
                return;
            }
            long cid = Long.valueOf(vals[0]);// get cid
            String name = vals[1];// get name
            // set customer
            if (length == 3) {
                String phone = vals[2];
                mapOutputKey.set(cid);
                mapOutputValue.set("customer", name + "," + phone);
            }
            // set order
            if (length == 4) {
                String price = vals[2];
                String date = vals[3];
                // set
                mapOutputKey.set(cid);
                mapOutputValue.set("order", name + "," + price + "," + date);
            }
            // output
            context.write(mapOutputKey, mapOutputValue);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    // step2: Reducer Class
    public static class DataJoinReducer extends Reducer<LongWritable, DataJoinWritable, NullWritable, Text> {

        private Text outputValue = new Text();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
        }

        @Override
        protected void reduce(LongWritable key, Iterable<DataJoinWritable> values, Context context) throws IOException, InterruptedException {
            String customerInfo = null;
            List<String> orderList = new ArrayList<>();
            // iterator
            for (DataJoinWritable value : values) {
                if ("customer".equals(value.getTag())) {
                    customerInfo = value.getData();
                } else if ("order".equals(value.getTag())) {
                    orderList.add(value.getData());
                }
            }
            // output
            for (String order : orderList) {
                // set output value
                outputValue.set(key.get() + "," + customerInfo + "," + order);
                // output
                context.write(NullWritable.get(), outputValue);
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    // step3: Driver
    public int run(String[] args) throws Exception {
        Configuration configuration = this.getConf();
        Job job = Job.getInstance(configuration, this.getClass().getSimpleName());
        job.setJarByClass(DataJoinWCMapReduce.class);

        // set job
        // input
        Path inpath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inpath);

        // output
        Path outpath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outpath);

        // Mapper
        job.setMapperClass(DataJoinMapper.class);
        // TODO
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(DataJoinWritable.class);

        // reducer
        job.setReducerClass(DataJoinReducer.class);
        // TODO
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        // submit job
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        args = new String[]{"hdfs://hadoop-senior01:8020/user/chenshun/order/input", "hdfs://hadoop-senior01:8020/user/chenshun/order/output2"};
        Configuration configuration = new Configuration();
        int status = ToolRunner.run(configuration, new DataJoinWCMapReduce(), args);
        // s program
        System.exit(status);
    }

}
