package com.example.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/hello")
@RefreshScope
public class HelloController {


    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${message}")
    private String message;

    @GetMapping("/index")
    public String index(){
        return  applicationName  ;
    }

    @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }
}
