package com.niksob.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.niksob.gateway",
        "com.niksob.authorization_model"
})
public class MainGatewayService {

    public static void main(String[] args) {
        SpringApplication.run(MainGatewayService.class, args);
    }
}
