package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.springcloud")
public class ApiFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiFeignApplication.class, args);
    }

}
