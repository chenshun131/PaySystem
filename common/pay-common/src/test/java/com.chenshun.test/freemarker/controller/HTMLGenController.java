package com.chenshun.test.freemarker.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/3/2 16:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Controller
public class HTMLGenController {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genhtml")
    @ResponseBody
    public String genHtml() throws Exception {
        //1、从spring容器中获得FreeMarkerConfigurer对象。
        //2、从FreeMarkerConfigurer对象中获得Configuration对象。
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        //3、使用Configuration对象获得Template对象。
        Template template = configuration.getTemplate("hello.ftl");
        //4、创建数据集
        Map data = new HashMap<>();
        data.put("hello", "spring freemarker test");
        //5、创建输出文件的Writer对象。
        Writer out = new FileWriter(new File("E:/temp/javaee28/out/test.html"));
        //6、使用模板对象的process方法输出文件
        template.process(data, out);
        //7、关闭流
        out.close();
        return "OK";
    }

}