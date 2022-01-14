package com.example.statertest.service;

/**
 * author  fengguangwu
 * createTime  2022/1/6
 * desc
 **/
public interface RedisService {

    void set(String key,String value);
    Object get(String key);
}
