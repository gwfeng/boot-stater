package com.example.redisspringbootstater;

import com.example.redisspringbootstater.redis.JedisPoolConfigProperties;
import com.example.redisspringbootstater.redis.RedisCondition;
import com.example.redisspringbootstater.redis.RedisProperties;
import com.example.redisspringbootstater.service.RedisService;
import com.example.redisspringbootstater.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * author  fengguangwu
 * createTime  2022/1/6
 * desc
 **/
@Configuration
@EnableConfigurationProperties({RedisProperties.class, JedisPoolConfigProperties.class})
public class RedisAutoConfiguration {
//    @Bean
//    @ConditionalOnMissingBean
//    public JedisPoolConfig jedisPoolConfig(JedisPoolConfigProperties jedisPoolConfigProperties){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(jedisPoolConfigProperties.getMaxTotal());
//        jedisPoolConfig.setMaxIdle(jedisPoolConfigProperties.getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(jedisPoolConfigProperties.getMaxWait());
//        jedisPoolConfig.setTestOnBorrow(jedisPoolConfigProperties.isTestOnBorrow());
//        jedisPoolConfig.setTestOnReturn(jedisPoolConfigProperties.isTestOnReturn());
//        jedisPoolConfig.setTestWhileIdle(jedisPoolConfigProperties.isTestWhileIdle());
//        return jedisPoolConfig;
//    }
//
//    @Autowired
//    private JedisPoolConfig jedisPoolConfig;

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

        //??????redis ???????????????????????????setpassword
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(),
                redisProperties.getTimeout());
        return jedisPool;
    }

    /**
     *  ?????????redis service ?????????????????????????????????demo ??????????????????????????????bean
     * @return
     */
    @Bean
    public RedisService redisService(){
        return new RedisServiceImpl();
    }
}
