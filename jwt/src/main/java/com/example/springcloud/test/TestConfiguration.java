package com.example.springcloud.test;

import com.example.springcloud.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration  {

    public  TestConfiguration() {
        System.out.println("TestConfiguration 容器启动初始化。。。");
    }

    // @Bean注解注册bean,同时可以指定初始化和销毁方法
  /*  @Bean
    public JwtUtils testBean() {
        JwtUtils jwtUtils = new JwtUtils();
        return  jwtUtils;
    }*/
}
