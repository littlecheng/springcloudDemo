package com.example.springcloud.controller;

import com.example.springcloud.fegin.FeginDept;
import com.example.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    private FeginDept feginDept;

    @GetMapping("/select")
    public List<Dept> get(){
        return feginDept.selectAll();
    }


    @GetMapping("/select/{id}")
    public Dept selectByID(@PathVariable(name="id") Long id){
        return feginDept.selectByID(id);
    }

    @PostMapping(value = "/save",produces = "application/json")
    public boolean saveDept(@RequestBody  Dept dept){
        return feginDept.saveDept(dept);
    }

    //假如服务提供者没启动,直接访问/handle/getInfo 那么容错机制就会调用FeginDept  fallback属性设置FeginError 里的方法
    @GetMapping("/getInfo")
    public String getInfo(){
        return feginDept.getInfo();
    }
}
