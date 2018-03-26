package com.chenshun.test.test;

import com.chenshun.test.mapper.AppMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * User: chenshun131 <p />
 * Time: 18/3/15 21:26  <p />
 * Version: V1.0  <p />
 * Description:
 * 建表 SQL : CREATE TABLE emp (id int(22) NOT NULL AUTO_INCREMENT, ename varchar(50) DEFAULT NULL,deptno varchar(50) DEFAULT NULL,PRIMARY KEY (id))
 * <p/>
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AppMapper mapper = context.getBean(AppMapper.class);

        String sql = "select * from emp";
        List<Map<String, Object>> result1 = mapper.executeQuery(sql);
        List<Map<String, Object>> result2 = mapper.executeQuery2(sql);

        System.out.println(result1);
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println(result2);

        context.close();
    }

}
