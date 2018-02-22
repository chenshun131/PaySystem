package com.chenshun.helper;

import com.alibaba.fastjson.annotation.JSONField;
import wusc.edu.pay.common.utils.date.DateOperator;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/6/20 18:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ReqLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户使用的客户端 */
    private String userAgent = null;

    /** IP地址 */
    private String ip = null;

    /** 请求地址 */
    private String requestPath = null;

    /** 请求参数 */
    private Map<?, ?> reqParamMap = null;

    /** 请求方法的完全限定名 */
    private String methodName = null;

    /** 响应结果 */
    private Map<String, Object> respMap = null;

    /** 开始时间 */
    private long startTimeMillis = 0;

    /** 结束时间 */
    private long endTimeMillis = 0;

    /** 请求时间字符串[yyyy-MM-dd HH:mm:ss] */
    private String optTime;

    /** 执行耗时[单位:ms] */
    private long executeConsuming;

    //endTimeMillis - startTimeMillis

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Map<?, ?> getReqParamMap() {
        return reqParamMap;
    }

    public void setReqParamMap(Map<?, ?> reqParamMap) {
        this.reqParamMap = reqParamMap;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, Object> getRespMap() {
        return respMap;
    }

    public void setRespMap(Map<String, Object> respMap) {
        this.respMap = respMap;
    }

    @JSONField(serialize = false, deserialize = true)
    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    @JSONField(serialize = false, deserialize = true)
    public long getEndTimeMillis() {
        return endTimeMillis;
    }

    public void setEndTimeMillis(long endTimeMillis) {
        this.endTimeMillis = endTimeMillis;
    }

    public String getOptTime() {
        this.optTime = DateOperator.formatDate(new Date(startTimeMillis), DateOperator.FORMAT_STR_WITH_TIME);
        return this.optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public long getExecuteConsuming() {
        this.executeConsuming = endTimeMillis - startTimeMillis;
        return this.executeConsuming;
    }

    public void setExecuteConsuming(long executeConsuming) {
        this.executeConsuming = executeConsuming;
    }

    public ReqLogInfo() {
        super();
    }

    public ReqLogInfo(String userAgent, String ip, String requestPath, Map<?, ?> reqParamMap, String methodName,
                      Map<String, Object> respMap, long startTimeMillis, long endTimeMillis) {
        super();
        this.userAgent = userAgent;
        this.requestPath = requestPath;
        this.ip = ip;
        this.reqParamMap = reqParamMap;
        this.methodName = methodName;
        this.respMap = respMap;
        this.startTimeMillis = startTimeMillis;
        this.endTimeMillis = endTimeMillis;
    }

}
