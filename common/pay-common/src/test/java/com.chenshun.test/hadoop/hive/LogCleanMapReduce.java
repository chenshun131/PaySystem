package com.chenshun.test.hadoop.hive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

/**
 * 编写MapReduce程序清理日志
 */
public class LogCleanMapReduce extends Configured implements Tool {

    /**
     * MapReduce 程序的入口
     * @param args
     */
    public static void main(String[] args) {
        // 获取配置信息
        Configuration conf = new Configuration();
        int res = 0 ;
        try {
            // 使用ToolRunner运行程序
            res = ToolRunner.run(conf, new LogCleanMapReduce(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // Exit Program
            System.exit(res);
        }
    }

    /**
     *  创建Job并运行Job
     * @param args
     * @return
     * @throws Exception
     */
    public int run(String[] args) throws Exception {
        // Get Configuration Instance
        Configuration conf = this.getConf() ;

        // Create Job
        Job job = Job.getInstance(conf, "logclean");
        job.setJarByClass(LogCleanMapReduce.class);

        // Set Job
        Path inPath = new Path(args[0]) ;
        FileInputFormat.setInputPaths(job, inPath);

        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        // Set Reduce Task Number
        job.setNumReduceTasks(0);

        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        /**
         * 清理已存在的输出文件
         */
        /**
            FileSystem fs = FileSystem.get( this.getConf());
            if (fs.exists(outPath)) {
                fs.delete(outPath, true);
            }
        */

        // Submit Job
        boolean success = job.waitForCompletion(true);
        if (success) {
            System.out.println("Clean process success!");
        } else {
            System.out.println("Clean process failed!");
        }
        // Return Status
        return success? 0 : 1;
    }

    /**
     * Mapper 类及实现map方法
     */
    static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        // 日志解析工具类
        LogParser logParser = new LogParser();
        Text outputKey = new Text();

        /**
         * map()
         */
        public void map(LongWritable key, Text value, Context context)
                throws java.io.IOException, InterruptedException {
            final String[] parsed = logParser.parse(value.toString());

            // step1.过滤掉静态资源访问请求
            if (parsed[2].startsWith("GET /static/")
                    || parsed[2].startsWith("GET /uc_server")) {
                return;
            }

            // step2.过滤掉开头的指定字符串
            if (parsed[2].startsWith("GET /")) {
                parsed[2] = parsed[2].substring("GET /".length());
            } else if (parsed[2].startsWith("POST /")) {
                parsed[2] = parsed[2].substring("POST /".length());
            }

            // step3.过滤掉结尾的特定字符串
            if (parsed[2].endsWith(" HTTP/1.1")) {
                parsed[2] = parsed[2].substring(0, parsed[2].length()
                        - " HTTP/1.1".length());
            }

            // step4.只写入前三个记录类型项
            outputKey.set(parsed[0] + "\t" + parsed[1] + "\t" + parsed[2]);

            // map output
            context.write(outputKey, NullWritable.get());
        }
    }

    /*
     * 日志解析工具类
     */
    static class LogParser {
        // 30/Oct/2016:17:38:20
        public static final SimpleDateFormat FORMAT = new SimpleDateFormat(
                "d/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        // 20161030173820
        public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat(
                "yyyyMMddHHmmss");

        /**
         * 解析日志的行记录
         *
         * @param line
         * @return 数组含有5个元素，分别是ip、时间、url、状态、流量
         */
        public String[] parse(String line) {
            String ip = parseIP(line);
            String time = parseTime(line);
            String url = parseURL(line);
            String status = parseStatus(line);
            String traffic = parseTraffic(line);

            return new String[]{ip, time, url, status, traffic};
        }

        private String parseTraffic(String line) {
            final String trim = line.substring(line.lastIndexOf("\"") + 1)
                    .trim();
            String traffic = trim.split(" ")[1];
            return traffic;
        }

        private String parseStatus(String line) {
            final String trim = line.substring(line.lastIndexOf("\"") + 1)
                    .trim();
            String status = trim.split(" ")[0];
            return status;
        }

        private String parseURL(String line) {
            final int first = line.indexOf("\"");
            final int last = line.lastIndexOf("\"");
            String url = line.substring(first + 1, last);
            return url;
        }

        /**
         * 解析英文时间字符串
         *
         * @param string
         * @return
         * @throws ParseException
         */
        private Date parseDateFormat(String string) {
            Date parse = null;
            try {
                parse = FORMAT.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return parse;
        }

        private String parseTime(String line) {
            final int first = line.indexOf("[");
            final int last = line.indexOf("+0800]");
            String time = line.substring(first + 1, last).trim();
            Date date = parseDateFormat(time);
            return DATEFORMAT.format(date);
        }

        private String parseIP(String line) {
            String ip = line.split("- -")[0].trim();
            return ip;
        }


        /**
         * 测试
         * @param args
         * @throws ParseException
         */
        public static void main(String[] args) throws ParseException {
            String srclog = "27.19.74.143 - - [30/May/2013:17:38:20 +0800] \"GET /static/image/common/faq.gif HTTP/1.1\" 200 1127";
            LogParser parser = new LogParser();
            String[] array = parser.parse(srclog);
            System.out.println("样例数据： " + srclog);
            System.out.format(
                    "解析结果：  ip=%s, time=%s, url=%s, status=%s, traffic=%s",
                    array[0], array[1], array[2], array[3], array[4]
            );
        }
    }
}