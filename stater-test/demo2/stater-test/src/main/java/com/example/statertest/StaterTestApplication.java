package com.example.statertest;

import com.example.redisspringbootstater.service.RedisService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StaterTestApplication implements CommandLineRunner {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StaterTestApplication.class,args);
        RedisService redisCacheService = context.getBean("redisService",RedisService.class);
         redisCacheService.set("name","fgw");
        Object object = redisCacheService.get("name");
        System.out.println(object);
        context.close();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
