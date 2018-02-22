package com.chenshun.helper;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wusc.edu.pay.common.utils.rest.RestResultDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/7/11 14:28  <p />
 * Version: V1.0  <p />
 * Description: OPEN API 请求日志切面 <p />
 */
@Aspect
public class LogReqAspect {

    private final Logger logger = LoggerFactory.getLogger(LogReqAspect.class);

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

    /**
     * 记录开始时间,方法调用前触发
     *
     * @param joinPoint
     */
    @Before("execution(* com.chenshun.studyapp..*Controller.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    /**
     * 记录结束时间,方法调用后触发
     *
     * @param joinPoint
     */
    @After("execution(* com.chenshun.studyapp..*Controller.*(..))")
    public void doAfter(JoinPoint joinPoint) {
        // 记录方法执行完成的时间
        endTimeMillis = System.currentTimeMillis();

        this.printOptLog();
    }

    /**
     * 环绕触发
     *
     * @param proceedingJoinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("execution(* com.chenshun.studyapp..*Controller.*(..))")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*----- 获取HttpServletRequest instance -----*/
        RequestAttributes reqAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletReqAttributes = (ServletRequestAttributes) reqAttributes;
        HttpServletRequest request = servletReqAttributes.getRequest();

        // 用户使用的客户端
        userAgent = request.getHeader("user-agent");
        // IP地址
        ip = request.getRemoteAddr();
        // 获取请求地址
        requestPath = request.getRequestURI();
        // 获取请求参数
        reqParamMap = request.getParameterMap();
        // 请求地址对应的方法的完全限定名
        methodName = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();
        // 执行完方法的返回值:调用proceed()方法,就会触发切入点方法执行
        respMap = new HashMap<>();
        // result就是被拦截方法的返回值
        Object result = proceedingJoinPoint.proceed();

        RestResultDTO restResultDTO = null;
        if (result != null) {
            if (result instanceof RestResultDTO) {
                restResultDTO = (RestResultDTO) result;
            }
        }
        if (restResultDTO != null) {
            // 只需要把响应码以及响应提示返回即可,返回的具体业务数据不予记录
            RestResultDTO restResultDTO4Log = new RestResultDTO();
            restResultDTO4Log.setStatusCode(restResultDTO.getStatusCode());
            restResultDTO4Log.setMessage(restResultDTO.getMessage());
            respMap.put("result", restResultDTO4Log);
        }
        return result;
    }

    /**
     * 打印操作日志
     */
    private void printOptLog() {
        ReqLogInfo reqLogInfo = new ReqLogInfo(userAgent, ip, requestPath, reqParamMap, methodName, respMap, startTimeMillis, endTimeMillis);
        logger.info(JSON.toJSONString(reqLogInfo));
    }

}
