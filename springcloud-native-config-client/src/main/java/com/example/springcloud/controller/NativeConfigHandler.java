package com.example.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class NativeConfigHandler {

    @Value("${server.port}")
    private String port;

    @Value("${foo}")
    private String foo;

    //端口访问config-server模块的configclient-dev.yml 里的server.port = 8070
    //访问  localhost:8070/config/index
    @GetMapping("/index")
    public String index(){
        return  this.port + "-"+ this.foo;
    }
}
