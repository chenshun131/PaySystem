package com.chenshun;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * User: chenshun131 <p />
 * Time: 18/2/21 19:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = "com.chenshun")
public class Swagger2 {

    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("支付系统")
                .description("微信、支付宝、银联支付服务")
                .termsOfServiceUrl("http://blog.52itstyle.com")
                .contact(new Contact("百度 ", "http://www.baidu.com", "1539831174@qq.com"))
                .version("1.0").build();
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
    }

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("支付后台API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chenshun.controller.LoginController"))
                .paths(PathSelectors.any()).build();
    }

}
