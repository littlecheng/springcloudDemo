package com.example.springcloud.controller;

import com.example.springcloud.pojo.Dept;
import com.example.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    //获取一些配置的信息，得到具体的微服务
    @Autowired
    private DiscoveryClient client;

    @Autowired
    DeptService deptService;

    @Value("${server.port}")
    private int port ;

    @GetMapping("/select")
    public List<Dept> get(){
       return deptService.selectAll();
    }


    @GetMapping("/select/{id}")
    public Dept selectByID(@PathVariable(name="id") Long id){
        return deptService.selectByID(id);
    }

    @PostMapping(value = "/save",produces = "application/json")
    public boolean saveDept(@RequestBody  Dept dept){
        return deptService.saveDept(dept);
    }


    @GetMapping("/getInfo")
    public String getInfo(){
        return "port:"+port;
    }

    //注册进来的微服务，获取一些信息。没有实际作用
    @RequestMapping("/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services "+services);

        //得到一个具体的微服务信息,通过具体的微服务id，此处是eureka页面中的status的名称
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-dept-8002");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t\t\t"+
                            instance.getPort()+"\t\t\t"+
                            instance.getUri()+"\t\t\t"+
                            instance.getServiceId());
        }
        return this.client;
    }
}
