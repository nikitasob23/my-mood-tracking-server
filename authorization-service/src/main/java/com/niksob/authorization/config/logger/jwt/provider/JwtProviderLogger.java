package com.niksob.authorization.config.logger.jwt.provider;

import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLogger;
import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLoggerImpl;
import com.niksob.domain_model.mapper.gson.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProviderLogger {

    private static final Class<JwtProvider> JWT_PROVIDER_CLASS = JwtProvider.class;

    private final Logger logger = LoggerFactory.getLogger(JWT_PROVIDER_CLASS);

    @Bean("jwt_provider_logger")
    public ObjectStateLogger getLogger(JsonMapper jsonMapper) {
        return new ObjectStateLoggerImpl(logger, jsonMapper);
    }
}
