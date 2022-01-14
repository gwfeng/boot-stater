package com.example.redisspringbootstater.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author  fengguangwu
 * createTime  2022/1/6
 * desc
 **/
@ConfigurationProperties(prefix = "redis.pool")
@Data
public class JedisPoolConfigProperties {
    private int maxTotal; //最大连接数

    private int maxIdle; //最大空闲连接数

    private int minIdle; //初始化连接数

    private long maxWait; //最大等待时间

    private boolean testOnBorrow; //对拿到的connection进行validateObject校验

    private boolean testOnReturn; //在进行returnObject对返回的connection进行validateObject校验

    private boolean testWhileIdle; //定时对线程池中空闲的链接进行validateObject校验

}
