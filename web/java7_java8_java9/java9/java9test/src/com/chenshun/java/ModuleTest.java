package com.chenshun.java;

import com.chenshun.bean.Person;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 14:14  <p />
 * Version: V1.0  <p />
 * Description: 测试 java 9 的模块化特性 <p />
 */
public class ModuleTest {

    private static final Logger LOGGER = Logger.getLogger("atguigu");

    public static void main(String[] args) {
        Person p = new Person("Tom", 12);
        System.out.println(p);

        LOGGER.info("aaaaaa");
    }

    @Test
    public void test1() {
        try {
            URL url = new URL("http://www.atguigu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
