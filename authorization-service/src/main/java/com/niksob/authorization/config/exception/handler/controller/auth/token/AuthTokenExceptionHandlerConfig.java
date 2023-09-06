package com.niksob.authorization.config.exception.handler.controller.auth.token;

import com.niksob.authorization.config.exception.ExceptionMessagesConfig;
import com.niksob.authorization.controller.token.exception.AuthTokenExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ExceptionMessagesConfig.class)
@AllArgsConstructor
public class AuthTokenExceptionHandlerConfig {

    private final ExceptionMessagesConfig exceptionMessagesConfig;

    @Bean("auth_token_exception_handler")
    public AuthTokenExceptionHandler getAuthTokenExceptionHandler() {
        return new AuthTokenExceptionHandler(exceptionMessagesConfig);
    }
}
