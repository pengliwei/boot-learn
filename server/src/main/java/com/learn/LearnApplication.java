package com.learn;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: 启动类
 * @author: PENGLW
 * @date: 2020/8/30
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.learn.system.dao")
@EnableScheduling
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }

}
