package com.zsl.boss.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.zsl.boss.demo.mapper")//参考博文 http://www.mybatis.org/mybatis-3/zh/index.html
public class BossApplication {

    public static void main(String[] args) {
        //SpringApplication.run(BossApplication.class, args);
        // 启动颜色格式化
        // 这不是唯一启动颜色格式的方式，有兴趣的同学可以查看源码
        /**
         * 1. AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
         * 2. 在`src/main/resources`目录下新建文件`application.properties`,
         *    内容为：`spring.output.ansi.enabled=always`
         *
         * 重要：如果配置第二种方式，第一种方式就不会起作用
         */
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        new SpringApplicationBuilder(BossApplication.class)//
                .main(SpringVersion.class) // 这个是为了可以加载 Spring 版本
                .bannerMode(Banner.Mode.CONSOLE)// 控制台打印
                .run(args);
    }
    /**https://www.51zxw.net/list.aspx?cid=651
     * shiro https://www.w3cschool.cn/shiro/rk3s1ifb.html
     * 项目总结 2019 - 8 - 13 张胜利
     * 项目名：Boss直聘招聘端和应聘端
     * 软件环境:IDEA + MAVEN + Tomcat9.x + Mysql5.x + GIT
     * 项目描述：仿造Boss直聘，之前做的安卓开发，所以知道大概需要哪些接口
     * 将Boos里面我所能知道需要的全部接口自己写了一遍
     * 包含了大概30张表（包含关联表）
     * 技术选型：SpringBoot + Mybatis + Redis + Shiro + Swagger2
     * 微信第三方登录 + 阿里验证码
     */
}
