package spring2.helloworld;

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
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring2/applicationContext.xml");

        // 2. 从 IOC 容器中获取 bean 实例
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");

        // 3. 调用 bean 的方法
        helloWorld.hello();

        HelloWorld helloWorld2 = (HelloWorld) ctx.getBean("helloWorld");
        System.out.println(helloWorld == helloWorld2);

        // 4. 关闭容器
        ctx.close();
    }

}
