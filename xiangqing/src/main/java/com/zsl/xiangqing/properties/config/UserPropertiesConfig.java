package com.zsl.xiangqing.properties.config;

import com.zsl.xiangqing.properties.UserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = UserProperties.class)
public class UserPropertiesConfig {


}
