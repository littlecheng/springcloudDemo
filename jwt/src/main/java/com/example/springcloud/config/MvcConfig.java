package com.example.springcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("addResourceHandlers()");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("addViewControllers()");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signUp").setViewName("signUp");
    }


}
