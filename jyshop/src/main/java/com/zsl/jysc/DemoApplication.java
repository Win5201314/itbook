package com.zsl.jysc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.SpringVersion;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching //开启缓存注解
@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.zsl.jysc.mapper")//参考博文 http://www.mybatis.org/mybatis-3/zh/index.html
@SpringBootApplication(scanBasePackages = "com.zsl.jysc")
//@ServletComponentScan(basePackages = "com.zsl.xiangqing.filter") //扫描自定义的过滤器
public class DemoApplication {

    public static void main(String[] args) {
        //SprTransactioningApplication.run(XiangqingApplication.class, args);
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
        new SpringApplicationBuilder(DemoApplication.class)//
                .main(SpringVersion.class) // 这个是为了可以加载 Spring 版本
                .bannerMode(Banner.Mode.CONSOLE)// 控制台打印
                .run(args);
    }
    /**jwt
     * Springboot + Mybatis
     * 1.Springboot的bannner:自定义Banner 博客地址 https://www.jianshu.com/p/a53f324c92f2
     * 2.SpringBoot配置MySql数据库和Druid连接池：https://www.cnblogs.com/feiyangbahu/p/9842363.html
     * 查看SQL监控：http://localhost:8080/druid/login.html
     * 3.SpringBoot配置mybatis:https://www.cnblogs.com/feiyangbahu/p/9843209.html
     * 4.Springboot配置redis
     * 5.Swagger2配置 参考博文 https://www.cnblogs.com/jtlgb/p/8532433.html
     *               注解 https://segmentfault.com/a/1190000017163807?utm_source=tag-newest
     *              访问地址： http://localhost:8080/swagger-ui.html
     *  6.logback日志配置:https://www.cnblogs.com/zhangjianbing/p/8992897.html
     *  7.Shiro + redis:https://www.cnblogs.com/UncleWang001/articles/9779245.html
     *  8.阿里云短信服务:https://blog.csdn.net/qq_38225558/article/details/84954935
     *  自定义注解 + jwt
     *  微信支付 + 支付宝支付
     *
     *  优惠券模块 暂时不写
     */

}
