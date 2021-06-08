package com.example.springcloud.controller;

import com.example.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    private static final  String REST_URL_PREFIX = "http://localhost:8001";//要调用的接口

    @GetMapping("/select/{id}")
    public Dept selectByID(@PathVariable(name="id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/select/{id}",Dept.class,id);
    }

    @GetMapping("/select")
    public List<Dept> get(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/select",List.class);
    }

    @RequestMapping(value = "/save",produces = "application/json")
    public boolean saveDept(@RequestBody Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/save",dept,Boolean.class);
    }

}
