package com.chenshun.test.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/3/2 15:33  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestFreeMarker {

    private static final String TEMPLATE_DIR = "/Users/mew/Desktop/AllMyFile/CompanyReposity/GitHub/PaySystem/common/pay-common/src/test/resources" +
            "/ftl";

    @Test
    public void testFreeMarker() throws Exception {
        // 创建一个模板文件
        // 创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
        // 设置模板的字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        // 使用Configuration对象加载一个模板文件，需要指定模板文件的文件名
        Template template = configuration.getTemplate("hello.ftl");
        // 创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map<String, Object> data = new HashMap<>();
        data.put("hello", "hello freemarker");
        // 创建一个Writer对象，指定输出文件的路径及文件名
        Writer out = new FileWriter(new File("/Users/mew/Desktop/hello.html"));
        // 使用模板对象的process方法输出文件
        template.process(data, out);
        // 关闭流
        out.close();
    }

    @Test
    public void testFreeMarker2() throws Exception {
        // 创建一个模板文件
        // 创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
        // 设置模板的字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        // 使用Configuration对象加载一个模板文件，需要指定模板文件的文件名
        Template template = configuration.getTemplate("student.ftl");
        // 创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map<String, Object> data = new HashMap<>();
        Student student = new Student(1, "小明", 11, "北京昌平回龙观");
        data.put("student", student);
        // 创建一个Writer对象，指定输出文件的路径及文件名
        Writer out = new FileWriter(new File("/Users/mew/Desktop/student.html"));
        // 使用模板对象的process方法输出文件
        template.process(data, out);
        // 关闭流
        out.close();
    }

    @Test
    public void testFreeMarker3() throws Exception {
        // 创建一个模板文件
        // 创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
        // 设置模板的字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        // 使用Configuration对象加载一个模板文件，需要指定模板文件的文件名
        Template template = configuration.getTemplate("student2.ftl");
        // 创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map<String, Object> data = new HashMap<>();
        Student student = new Student(1, "小明", 11, "北京昌平回龙观");
        data.put("student", student);
        // 创建一个Writer对象，指定输出文件的路径及文件名
        Writer out = new FileWriter(new File("/Users/mew/Desktop/student2.html"));
        // 使用模板对象的process方法输出文件
        template.process(data, out);
        // 关闭流
        out.close();
    }

    @Test
    public void testFreeMarkerWithSpring() {
        ApplicationContext applicaitonContext = new FileSystemXmlApplicationContext("classpath:springApplication-freemarker.xml");
    }

}
