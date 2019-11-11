package com.zsl.xiangqing.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "usersproperties")
public class UserProperties {
    /**
     * 密码最多尝试次数
     */
    @NestedConfigurationProperty
    private String passwordMaxRetryCount;

    /**
     * 验证码超时最长时间
     */
    @NestedConfigurationProperty
    private String codeExpireTime;

}
