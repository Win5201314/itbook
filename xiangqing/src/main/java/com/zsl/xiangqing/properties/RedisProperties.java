package com.zsl.xiangqing.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    /**
     * redis主机
     */
    private String host;

    /**
     * redis端口
     */
    private int port;

    /**
     * 超时
     */
    private int timeout;

    /**
     * 密码
     */
    private String password;
}
