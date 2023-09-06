package com.niksob.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.niksob.domain_model",
        "com.niksob.authorization_model",
        "com.niksob.database_model",
        "com.niksob.authorization"
})
public class MainAuthorizationService {

    public static void main(String[] args) {
        SpringApplication.run(MainAuthorizationService.class, args);
    }
}
