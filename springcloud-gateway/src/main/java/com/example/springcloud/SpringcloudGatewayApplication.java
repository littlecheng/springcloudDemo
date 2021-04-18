package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringcloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/dept/**")
                        .uri("lb://provider"))
                .route(r->r.path("/user/**")
                        .uri("lb://provider"))
                .build();
    }

}
