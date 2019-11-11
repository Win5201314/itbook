package com.zsl.xiangqing.properties.config;

import com.zsl.xiangqing.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = RedisProperties.class)
public class RedisPropertiesConfig {
}
