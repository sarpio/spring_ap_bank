package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(a -> a
//                        .path("/api/account/**")
//                        .filters(f -> f.rewritePath("/api/account/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", new Date().toString()))
//                        .uri("lb://ACCOUNT-SERVICE")).build();
//    }

}
