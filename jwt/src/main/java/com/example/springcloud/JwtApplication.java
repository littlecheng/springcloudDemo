package com.example.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springcloud.mapper")
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }
}
