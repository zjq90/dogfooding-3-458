package com.zoushiyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zoushiyou.mapper")
public class ZsyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZsyApplication.class, args);
    }
}
