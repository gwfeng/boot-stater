package com.example.statertest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StaterTestApplication implements CommandLineRunner {
    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(StaterTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        redisService.set("key","fffffff");
        System.out.println(redisService.get("key"));
    }
}
