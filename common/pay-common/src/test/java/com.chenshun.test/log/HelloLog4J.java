package com.chenshun.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: chenshun131 <p />
 * Time: 18/1/15 22:19  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HelloLog4J {

    private static Logger logger = LoggerFactory.getLogger(HelloLog4J.class);

    public static void main(String[] args) {
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录warn级别的信息
        logger.warn("This is warn message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }

}
