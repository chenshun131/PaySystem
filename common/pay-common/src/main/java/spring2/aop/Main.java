package spring2.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Main {

    public static void main(String[] args) {
//		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();
//		
//		arithmeticCalculator = new ArithmeticCalculatorLoggingProxy(arithmeticCalculator).getLoggingProxy();
//		
//		int result = arithmeticCalculator.add(11, 12);
//		System.out.println("result:" + result);
//		
//		result = arithmeticCalculator.div(21, 3);
//		System.out.println("result:" + result);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring2/applicationContext-aop.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

        System.out.println(arithmeticCalculator.getClass().getName());

        int result = arithmeticCalculator.add(11, 12);
        System.out.println("result:" + result);

        result = arithmeticCalculator.div(21, 3);
        System.out.println("result:" + result);
    }

}
