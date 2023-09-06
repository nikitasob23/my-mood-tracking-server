package com.niksob.authorization.config.logger.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth.logger")
@Getter
@Setter
public class LoggerMessageConfig {
    private String userAlreadyExists;
    private String databaseError;

    private String expiredAuthTokenTemplate;

    private String usernameNotFoundTemplate;
    private String wrongPassword;

    private String succeedControllerWorkTemplate;
    private String failedControllerWorkTemplate;

    private String succeedJwtGenerationTemplate;
    private String jwtIsValidTemplate;
    private String jwtIsNotValidTemplate;
    private String expiredJwtTokenTemplate;
    private String unknownErrorWithJwtGenerationTemplate;
}
