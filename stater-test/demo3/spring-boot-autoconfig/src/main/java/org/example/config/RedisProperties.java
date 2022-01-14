package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author  fengguangwu
 * createTime  2022/1/6
 * desc
 **/
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {
    private String host;
    private Integer port;
    private Integer timeout;
    private String password;
}
