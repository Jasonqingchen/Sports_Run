package com.example.LqcSpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@Configuration
@EnableScheduling
@EnableCaching
@MapperScan("com.example.LqcSpringBoot.mapper")
public class LqcSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LqcSpringBootApplication.class, args);
    }
}

