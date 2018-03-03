package spring2.ref;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Main {

    public static void main(String[] args) {
        // 1. 创建 IOC 容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring2/applicationContext-annotation.xml");

        UserAction userAction = (UserAction) ctx.getBean("userAction");
        userAction.execute();
    }

}
