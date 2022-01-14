package com.example.demo;

import com.demo.starter.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource(name = "demo")
    public DemoService demoService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(demoService.say());
    }
}
