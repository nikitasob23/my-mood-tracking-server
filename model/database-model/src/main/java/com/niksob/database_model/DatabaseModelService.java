package com.niksob.database_model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.niksob.database_model",
        "com.niksob.domain_model"
})
public class DatabaseModelService {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseModelService.class, args);
    }
}
