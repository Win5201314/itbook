package com.zsl.xiangqing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 参考博文 https://www.cnblogs.com/jtlgb/p/8532433.html
 * 注解 https://segmentfault.com/a/1190000017163807?utm_source=tag-newest
 * 访问地址： http://localhost:8080/swagger-ui.html
 */
@Configuration
//@Profile({"dev", "test", "prod"})//只在开发和测试环境开启
//@EnableSwaggerBootstrapUI
@EnableSwagger2
public class Swagger2 {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zsl.xiangqing.controller"))//这里填写controller类路径
                .paths(PathSelectors.any()) .build();
    }
    /** *
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     * */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring MVC中使用Swagger2构建RESTful APIs")
                .description("")
                .termsOfServiceUrl("http://www.baidu.com")
                //.contact("shao")
                .version("1.0")
                .build();
    }
}
