package com.chenshun.transformer.mr.etl;

import com.chenshun.transformer.common.EventLogConstants;
import com.chenshun.transformer.common.EventLogConstants.EventEnum;
import com.chenshun.transformer.common.GlobalConstants;
import com.chenshun.transformer.util.LoggerUtil;
import com.chenshun.transformer.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.zip.CRC32;

/**
 * 从hdfs读取原始日志数据，然后经过处理后，将日志数据输出到HBase，不需要reduce进行数据聚合操作<br/>
 * 输入的key/value由于不关注输入的key，key类型设置为Object，value为Text<br/>
 * 输出的key为null，value为Put对象
 *
 * @author ibf
 */
public class AnalysisDataMapper extends Mapper<Object, Text, NullWritable, Put> {

    private static final Logger logger = Logger.getLogger(AnalysisDataMapper.class);

    private byte[] family = EventLogConstants.BYTES_EVENT_LOGS_FAMILY_NAME;

    private CRC32 crc32 = new CRC32();

    private long currentMillis = -1L;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 实质上来将，默认运行昨天，但是我可以指定日志来进行运行
        Configuration conf = context.getConfiguration();
        this.currentMillis = TimeUtil.parseString2Long(conf.get(GlobalConstants.RUNNING_DATE_PARAMES));
    }

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String logText = value.toString();
        // 1. 日志解析+数据补全
        Map<String, String> clientInfo = LoggerUtil.handleLogText(logText);

        // 2. 数据过滤
        // 2.1 解析数据失败进行过滤
        if (clientInfo == null || clientInfo.isEmpty()) {
            // 结束当前记录的处理，表示日志解析失败
            logger.debug("日志解析失败，当前日志数据为:" + logText);
            return;
        }
        // 2.2 判断解析之后的数据是否异常，比如是否缺少数据
        if (!this.filterEventData(clientInfo, logText)) {
            // 表示有必须存在的字段属性在clientInfo中没有找到
            logger.debug("当前数据缺少关键性字段，日志为:" + logText);
            return;
        }

        // 3. 结果输出
        // 3.1 构建输出对象Put
        Put put = this.generateHBasePut(clientInfo, logText);
        // 3.2 数据输出
        context.write(NullWritable.get(), put);
    }

    /**
     * 进行依赖事件类型进行字段数据判断，如果有必要字段不存在，返回false。如果所有必要字段存在，返回true
     *
     * @param clientInfo
     * @param logText
     * @return
     */
    private boolean filterEventData(Map<String, String> clientInfo, String logText) {
        // 1. 获取数据类型 ==> 事件类型
        String eventAliasName = clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME);
        EventEnum event = EventEnum.valueOfAlias(eventAliasName);

        // 2. 过滤无效的数据类型
        if (event == null) {
            // 没法处理该事件类型的数据
            logger.warn("没法处理当前数据对应的事件类型，事件值是:" + eventAliasName + ", 日志为:" + logText);
            return false;
        }

        // 3. 构建filter数据过滤
        // 3.1 构建共同的字段属性的过滤，比如platform、servertime...
        String platform = clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_PLATFORM);
        String serverTime = clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME);
        boolean result = StringUtils.isNotBlank(platform) && StringUtils.isNotBlank(serverTime);

        // 3.2 针对不同的平台、不同的事件进行数据过滤判断
        if (result) {
            switch (platform) {
                case EventLogConstants.PlatformNameConstants.PC_WEBSITE_SDK:
                    // WEB 平台
                    // 3.2.1 考虑web平台上公用的字段属性，比如会话id、访客id
                    result = result && StringUtils.isNotBlank(clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_SESSION_ID))
                            && StringUtils.isNotBlank(clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_UUID));

                    // 3.2.2 考虑不同事件的字段属性
                    switch (event) {
                        case LAUNCH:
                            // nothings
                            break;
                        case PAGEVIEW:
                            // 要求当前页面的url必须存在
                            result = result
                                    && StringUtils.isNotBlank(clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_CURRENT_URL));
                            break;
                        case EVENT:
                            // 要求ca和ac必须存在
                            // TODO: 自己完善
                            break;
                        case CHARGEREQUEST:
                            // 要求订单id、金额、支付方式、货币类型必须存在
                            // TODO: 自己完善
                            break;
                        default:
                            // web平台上不处理了该事件的数据
                            result = false;
                            break;
                    }
                    break;
                case EventLogConstants.PlatformNameConstants.JAVA_SERVER_SDK:
                    // JAVA 后台
                    // 要求会员id必须存在
                    // TODO: 自己完善
                    break;
                default:
                    // 没法处理该平台的数据
                    result = false;
                    break;
            }
        }

        // 4. 返回结果
        return result;
    }

    /**
     * 根据map参数创建put对象
     *
     * @param clientInfo
     * @param logText
     * @return
     */
    private Put generateHBasePut(Map<String, String> clientInfo, String logText) {
        // 1. 构建rowkey
        String uuid = clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_UUID);
        long serverTime = Long.valueOf(clientInfo.get(EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME));
        byte[] rowkey = this.generateRowKey(uuid, serverTime, clientInfo);

        // 2. 构建put
        Put put = new Put(rowkey);
        // 2.1 将clientInfo中的属性值添加到Put中
        for (Map.Entry<String, String> entry : clientInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                put.add(family, Bytes.toBytes(key), Bytes.toBytes(value));
            }
        }

        // 3. 返回put
        return put;
    }

    /**
     * 根据给定的参数产生rowkey， rowkey: 随机 + 尽可能的短小<br/>
     * 随机值 + 时间戳(当前过期的时间毫米数)
     *
     * @param uuid
     * @param serverTime
     * @param clientInfo
     * @return
     */
    private byte[] generateRowKey(String uuid, long serverTime, Map<String, String> clientInfo) {
        this.crc32.reset(); // 重置
        if (StringUtils.isNotBlank(uuid)) {
            this.crc32.update(Bytes.toBytes(uuid));
        }
        this.crc32.update(Bytes.toBytes(clientInfo.hashCode()));
        byte[] buf1 = Bytes.toBytes(this.crc32.getValue()); // 占用8个字节

        byte[] buf2 = Bytes.toBytes((int) (serverTime - this.currentMillis)); // 占用4个字节

        // 合并两个buffer
        byte[] buffer = new byte[buf1.length + buf2.length];
        System.arraycopy(buf1, 0, buffer, 0, buf1.length);
        System.arraycopy(buf2, 0, buffer, buf1.length, buf2.length);

        return buffer;
    }
}
