package org.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.ConnectException;

/**
 * author  fengguangwu
 * createTime  2022/1/7
 * desc
 **/
@Configuration
@EnableConfigurationProperties({RedisProperties.class, JedisPoolConfigProperties.class})
@ConditionalOnProperty(prefix = "redis",name = "host",matchIfMissing = false)
public class MyRedisAutoConfigulation {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(RedisCondition.class)
    public JedisPool jedisPool(RedisProperties redisProperties,JedisPoolConfigProperties jedisPoolConfigProperties) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(jedisPoolConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(jedisPoolConfigProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(jedisPoolConfigProperties.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(jedisPoolConfigProperties.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(jedisPoolConfigProperties.isTestOnReturn());
        jedisPoolConfig.setTestWhileIdle(jedisPoolConfigProperties.isTestWhileIdle());

        //如果redis 没有密码，则不需要setpassword
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(),
                redisProperties.getTimeout());
        return jedisPool;
    }


    @Bean
    public JedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setPort(redisProperties.getPort());
//        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        return jedisConnectionFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }
}
