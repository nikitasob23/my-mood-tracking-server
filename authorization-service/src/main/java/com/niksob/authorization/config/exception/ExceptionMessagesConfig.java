package com.niksob.authorization.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth.exception")
@Getter
@Setter
public class ExceptionMessagesConfig {
    private String usernameNotFound;
    private String userAlreadyExists;
    private String wrongPassword;
    private String database;
    private String expiredAuthToken;
}
