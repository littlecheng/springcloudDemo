package com.example.springcloud.fegin;

import com.example.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "provider",fallback = FeignError.class)
public interface FeginDept {

    @PostMapping(value = "/save",produces = "application/json")
    boolean saveDept(@RequestBody  Dept dept);


    @GetMapping("/dept/select/{id}")
    Dept selectByID(@PathVariable("id") Long id);

    @GetMapping("/dept/select")
    List<Dept> selectAll();


    @GetMapping("/dept/getInfo")
    String getInfo();

}
