package com.example.statertest.service.impl;

import com.example.statertest.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * author  fengguangwu
 * createTime  2022/1/6
 * desc
 **/
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisPool jedisPool;


    public Jedis getJedis(){
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            log.error("Get jedis from jedisPool,  error : ",e);
        }
        return jedis;
    }

    @Override
    public void set(String key,String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key,value);
        } catch (Exception e){
            log.error("error:",e);
        }finally {
            jedis.close();
        }
    }

    @Override
    public Object get(String key) {
        Jedis jedis = getJedis();
        Object o = null;
        try {
            o =  jedis.get(key);
        } catch (Exception e){
            log.error("error:",e);
        }finally {
            jedis.close();
        }
        return o;
    }
}
