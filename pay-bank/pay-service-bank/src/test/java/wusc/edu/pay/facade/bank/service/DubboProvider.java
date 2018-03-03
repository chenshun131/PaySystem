package wusc.edu.pay.facade.bank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述: 启动Dubbo服务用的MainClass
 * @作者: WuShuicheng
 * @创建时间: 2013-11-5,下午9:47:55
 * @版本: 1.0
 */
public class DubboProvider {

    private static final Logger logger = LoggerFactory.getLogger(DubboProvider.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring1/spring1-context.xml");
            context.start();
        } catch (Exception e) {
            logger.error("== DubboProvider context start error:", e);
        }
        synchronized (DubboProvider.class) {
            while (true) {
                try {
                    DubboProvider.class.wait();
                } catch (InterruptedException e) {
                    logger.error("== synchronized error:", e);
                }
            }
        }
    }

}