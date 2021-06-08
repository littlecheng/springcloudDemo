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

    //指定局部刷新
    // 访问该方法前,需要刷新 postman post方式 http://localhost:8080/actuator/bus-refresh/serviceAAA:8030
    // http://localhost:8080为configserver端口，
    // serviceAAA：8030
    // serviceAAA为远程配置的spring.application.name,8030为configclient设置的端口号server.port= 8030
    @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }
}
