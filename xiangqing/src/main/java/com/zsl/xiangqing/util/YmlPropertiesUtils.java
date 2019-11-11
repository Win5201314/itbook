package com.zsl.xiangqing.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * 读取yml格式文件配置
 */
public class YmlPropertiesUtils {

    private static String PROPERTY_NAME = "application.yml";

    public static Object getYml(Object key){
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(getYml("server.port"));
        System.out.println(getYml("spring.cache.timeout"));
    }
}
