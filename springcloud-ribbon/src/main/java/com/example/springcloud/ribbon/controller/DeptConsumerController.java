package com.example.springcloud.ribbon.controller;

import com.example.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 通过 localhost:8040/dept/select等方式调用
 */
@RestController
@RequestMapping("/dept")
public class DeptConsumerController {

    @Autowired
    private RestTemplate restTemplate;

   private static final  String APPLICATIONNAME = "http://provider";

    @GetMapping("/select/{id}")
    public Dept selectByID(@PathVariable(name="id") Long id){
        return restTemplate.getForObject(APPLICATIONNAME+"/dept/select/{id}",Dept.class,id);
    }

    @GetMapping("/select")
    public List<Dept> get(){
        return restTemplate.getForObject(APPLICATIONNAME+"/dept/select",List.class);
    }

    @RequestMapping(value = "/save",produces = "application/json")
    public boolean saveDept(@RequestBody Dept dept){
        return restTemplate.postForObject(APPLICATIONNAME+"/dept/save",dept,Boolean.class);
    }


    @GetMapping("/info")
    public String info(){
        return restTemplate.getForObject(APPLICATIONNAME+"/dept/getInfo",String.class);
    }

}
