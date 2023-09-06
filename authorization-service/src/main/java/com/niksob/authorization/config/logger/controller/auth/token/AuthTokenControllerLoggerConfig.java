package com.niksob.authorization.config.logger.controller.auth.token;

import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLogger;
import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLoggerImpl;
import com.niksob.authorization.logger.controller.token.AuthTokenControllerLogger;
import com.niksob.domain_model.mapper.gson.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthTokenControllerLoggerConfig {

    private static final Class<AuthTokenControllerLogger> AUTH_TOKEN_CONTROLLER_LOGGER_CLASS =
            AuthTokenControllerLogger.class;

    private final Logger log = LoggerFactory.getLogger(AUTH_TOKEN_CONTROLLER_LOGGER_CLASS);

    @Bean("auth_token_controller_logger")
    public ObjectStateLogger getObjectStateLogger(@Qualifier("json_mapper") JsonMapper jsonMapper) {
        return new ObjectStateLoggerImpl(log, jsonMapper);
    }
}
