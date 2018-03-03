package spring1.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring1/beans-annotation.xml");

        UserAction userAction = ctx.getBean(UserAction.class);
        userAction.execute();
    }

}
