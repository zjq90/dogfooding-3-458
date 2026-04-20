package com.zoushiyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.zoushiyou.mapper")
@EnableScheduling
public class ZsyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZsyApplication.class, args);
    }
}
