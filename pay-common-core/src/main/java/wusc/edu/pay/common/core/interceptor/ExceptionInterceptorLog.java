package wusc.edu.pay.common.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import wusc.edu.pay.common.exceptions.BizException;

import java.lang.reflect.Method;

/**
 * className：ExceptionInterceptorLog <br>
 * Function：异常的处理 拦截 <br>
 * date: 2014-12-16-上午10:12:37 <br>
 *
 * @author laich
 */
public class ExceptionInterceptorLog implements ThrowsAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptorLog.class);

    /**
     * 对未知异常的处理. <br>
     * Method method 执行的方法 Object[] args <br>
     * 方法参数 Object target <br>
     * 代理的目标对象 Throwable BizException 产生的异常 <br>
     */
    public void afterThrowing(Method method, Object[] args, Object target, BizException ex) {
        logger.info("==>ExceptionInterceptorLog.BizException");
        logger.info("==>errCode:" + ex.getCode() + " errMsg:" + ex.getMsg());
        logger.info("==>" + ex.fillInStackTrace());
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        logger.error("==>ExceptionInterceptorLog.Exception");
        logger.error("==>Error class: " + target.getClass().getName());
        logger.error("==>Error method: " + method.getName());

        for (int i = 0; i < args.length; i++) {
            logger.error("==>args[" + i + "]: " + args[i]);
        }

        logger.error("==>Exception class: " + ex.getClass().getName());
        logger.error("==>" + ex.fillInStackTrace());
        ex.printStackTrace();
    }

}
