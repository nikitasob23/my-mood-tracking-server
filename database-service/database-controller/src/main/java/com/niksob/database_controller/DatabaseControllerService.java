package com.niksob.database_controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.niksob.database_controller",
        "com.niksob.database_repository",
        "com.niksob.database_model",
        "com.niksob.domain_model"
})
@EnableJpaRepositories(basePackages = {"com.niksob.database_repository.repository"})
@EntityScan(basePackages = {"com.niksob.databaserepository.entity"})
public class DatabaseControllerService {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseControllerService.class, args);
    }

}
