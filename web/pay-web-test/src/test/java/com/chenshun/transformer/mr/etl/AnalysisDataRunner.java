package com.chenshun.transformer.mr.etl;

import com.chenshun.transformer.common.EventLogConstants;
import com.chenshun.transformer.common.GlobalConstants;
import com.chenshun.transformer.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.security.PrivilegedAction;

/**
 * etl代码入口类<br/>
 * job运行参数格式如下：<br/>
 * 默认处理昨天的数据，如果执行失败，需要后期人工干预===>需要指定运行那天的数据<br/>
 * hadoop jar /home/beifeng/bf_transform.jar package_name.AnalysisDataRunner -d
 * 2015-12-20<br/>
 * yarn jar /home/beifeng/bf_transform.jar package_name.AnalysisDataRunner -d
 * 2015-12-20<br/>
 *
 * @author ibf
 */
public class AnalysisDataRunner implements Tool {

    private Configuration conf = null;

    /**
     * main方法，执行入口
     *
     * @param args
     */
    public static void main(String[] args) {
        test1(args); // 本地运行和集群运行
//		test2(args); // 本地提交集群运行方式不讲
    }

    /**
     * 正常运行
     *
     * @param args
     */
    public static void test1(String[] args) {
        try {
            // 调用执行
            int exitCode = ToolRunner.run(new AnalysisDataRunner(), args);
            if (exitCode == 0) {
                System.out.println("运行成功");
            } else {
                System.out.println("运行失败");
            }
        } catch (Exception e) {
            System.err.println("job运行异常" + e);
        }
    }

    /**
     * 本地提交集群运行
     *
     * @param args
     */
    public static void test2(final String[] args) {
        // 解决权限异常问题
        UserGroupInformation.createRemoteUser("beifeng").doAs(new PrivilegedAction<Object>() {

            public Object run() {
                try {
                    // 调用执行
                    int exitCode = ToolRunner.run(new AnalysisDataRunner(), args);
                    if (exitCode == 0) {
                        System.out.println("运行成功");
                    } else {
                        System.out.println("运行失败");
                    }
                } catch (Exception e) {
                    System.err.println("job运行异常" + e);
                }
                return null;
            }
        });
    }

    @Override
    public Configuration getConf() {
        return this.conf;
    }

    @Override
    public void setConf(Configuration that) {
        // 添加自己的环境信息
        this.conf = HBaseConfiguration.create(that);
        /* 下面配置是本地提交集群运行 */
        // 添加一些参数
//		this.conf.set("fs.defaultFS", "hdfs://hadoop-senior01:8020");
//		this.conf.set("mapreduce.framework.name", "yarn");
//		this.conf.set("yarn.resourcemanager.address", "hadoop-senior01:8032");
//		this.conf.set("mapreduce.app-submission.cross-platform", "true"); // 当开发环境是windows，集群是linux时候，配置为true
        /* 上面配置是本地提交集群运行 结束 */

    }

    @Override
    public int run(String[] args) throws Exception {
        // 运行job的代码
        // 1. 获取程序运行的上下文
        Configuration conf = this.getConf();
        // 2. 处理参数
        this.processArgs(conf, args);

        // 3. 开始创建job,
        // 在创建完job后，conf这个对象就不要使用了，如果非要Configuration对象的话，使用job.getConfiguration()来获取
        Job job = Job.getInstance(conf, "etl");
        // 设置job参数
        job.setJarByClass(AnalysisDataRunner.class);
        // mapper参数
        job.setMapperClass(AnalysisDataMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Put.class);
        // 设置reducer参数
        job.setNumReduceTasks(0); // 没有reducer设置为0

        // 设置输入信息
        this.setJobInputPaths(job);

        // 设置输出到hbase的参数信息
        this.setHBaseOutputConfig(job);

        /* 本地提交集群运行，指定jar文件 */
        // 设置jar文件
        // 使用dev环境进行编译
//		((org.apache.hadoop.mapred.JobConf) job.getConfiguration()).setJar("target/transformer-0.0.1.jar");
        /* 本地提交集群运行，指定jar文件 结束 */

        // 成功返回0，失败返回-1, true的意思：阻塞等待job执行完成
        return job.waitForCompletion(true) ? 0 : -1;
    }

    /**
     * 处理参数，一般处理时间参数
     *
     * @param conf
     * @param args
     */
    private void processArgs(Configuration conf, String[] args) {
        String date = null;
        for (int i = 0; i < args.length; i++) {
            if ("-d".equals(args[i])) {
                if (i + 1 < args.length) {
                    date = args[++i];
                    break;
                }
            }
        }

        // 查看是否需要默认参数
        if (StringUtils.isBlank(date) || !TimeUtil.isValidateRunningDate(date)) {
            date = TimeUtil.getYesterday(); // 默认时间是昨天
        }
        // 保存到上下文中间
        conf.set(GlobalConstants.RUNNING_DATE_PARAMES, date);
    }

    /**
     * 设置job的输入参数<br/>
     * 使用默认的InputFormat读取HDFS上的原始日志文件
     *
     * @param job
     * @throws IOException
     */
    private void setJobInputPaths(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        // 获取要etl数据的日期是那一天
        String date = conf.get(GlobalConstants.RUNNING_DATE_PARAMES);
        // 文件名格式化
        String hdfsPath = TimeUtil.parseLong2String(TimeUtil.parseString2Long(date), "yyyy/MM/dd");
        if (GlobalConstants.HDFS_LOGS_PATH_PREFIX.endsWith("/")) {
            // 以反斜杠结尾
            hdfsPath = GlobalConstants.HDFS_LOGS_PATH_PREFIX + hdfsPath;
        } else {
            // 没有反斜杠
            hdfsPath = GlobalConstants.HDFS_LOGS_PATH_PREFIX + "/" + hdfsPath;
        }
        // hdfs的格式为: /eventLogs/yyyy/MM/dd ==>
        // ${fs.defaultFS}/eventLogs/2015/12/20 ==>
        // hdfs://hadoop-senior01:8020/eventLogs/2015/12/20

        // 默认情况下，FileSystem是单例的(针对同一个schema: hdfs\file....),FileSystem在多个线程中是共享的
        FileSystem fs = FileSystem.get(conf);
        Path inputPath = new Path(hdfsPath);
        if (fs.exists(inputPath)) {
            // 文件夹存在
            FileInputFormat.addInputPath(job, inputPath);
        } else {
            throw new RuntimeException("hdfs对应文件夹不存在:" + hdfsPath);
        }
        // 默认情况下，filesystem不要进行关闭操作，就是不要调用close方法 =====>>>>>> fs.close()不要调用
    }

    /**
     * 设置输出到hbase参数<br/>
     * hbase按天分表：表名格式为： event_logs_20151220<br/>
     * 由于是按天分表，所以需要使用java代码进行创建表<br/>
     *
     * @param job
     * @throws IOException
     */
    private void setHBaseOutputConfig(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        // 获取要etl数据的日期市那一天
        String date = conf.get(GlobalConstants.RUNNING_DATE_PARAMES);
        // 格式化hbase的后缀
        String tableNameSuffix = TimeUtil.parseLong2String(TimeUtil.parseString2Long(date),
                TimeUtil.HBASE_TABLE_NAME_SUFFIX_FORMAT);
        // 构建表名称
        String tableName = EventLogConstants.HBASE_NAME_EVENT_LOGS + tableNameSuffix;

        // 指定输出, 当不存在reducer的时候，reducer对于参数设置为null
        // 当本地提交集群运行的时候以及集群运行的时候，需要按照下面这行代码设置， addDependencyJars参数要求为true
//		TableMapReduceUtil.initTableReducerJob(tableName, null, job);

        // windows本地运行，需要使用下面代码, addDependencyJars参数要求为false
        TableMapReduceUtil.initTableReducerJob(tableName, null, job, null,
                null, null, null, false);

        // 更新hbase中对应表结构
        // 存在就删除，然后新建
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(conf);
        } catch (Exception e) {
            throw new RuntimeException("创建hbaseadmin对象失败", e);
        }

        try {
            TableName tn = TableName.valueOf(tableName);
            HTableDescriptor htd = new HTableDescriptor(tn);
            // 设置family
            htd.addFamily(new HColumnDescriptor(EventLogConstants.BYTES_EVENT_LOGS_FAMILY_NAME));
            // 设置其他参数
            // 。。。。

            // 判断表是否存在
            if (admin.tableExists(tn)) {
                // 存在删除
                if (admin.isTableEnabled(tn)) {
                    // 处于enabled状态
                    admin.disableTable(tn); // 修改状态
                }
                // 删除表
                admin.deleteTable(tn);
            }

            // 创建表
            // 预分区
            // byte[][] keySplits = new byte[3][]; // 3个区
            // keySplits[0] = Bytes.toBytes("1"); // （负无穷大，1]
            // keySplits[1] = Bytes.toBytes("2"); // (1,2)
            // keySplits[2] = Bytes.toBytes("3"); // [2,+x)
            // admin.createTable(htd, keySplits);
            admin.createTable(htd);
        } catch (Exception e) {
            throw new RuntimeException("创建habse表失败", e);
        } finally {
            if (admin != null) {
                try {
                    admin.close();
                } catch (Exception e) {
                    // 防止finaly语句块中的异常影响代码
                }
            }
        }
    }
}
