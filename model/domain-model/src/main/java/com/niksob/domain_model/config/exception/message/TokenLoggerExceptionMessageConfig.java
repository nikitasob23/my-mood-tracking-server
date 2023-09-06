package com.niksob.domain_model.config.exception.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "exception.message.logger.token")
@Getter
@Setter
public class TokenLoggerExceptionMessageConfig {
    private String notFoundTokenValuePattern;
    private String notAppliedTokenMask;
}
