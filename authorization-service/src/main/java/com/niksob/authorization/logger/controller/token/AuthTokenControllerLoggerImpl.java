package com.niksob.authorization.logger.controller.token;

import com.niksob.authorization.config.logger.message.LoggerMessageConfig;
import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLogger;
import com.niksob.authorization_model.exception.auth.WrongPasswordException;
import com.niksob.authorization_model.exception.auth.token.ExpiredAuthTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthTokenControllerLoggerImpl implements AuthTokenControllerLogger {

    private final LoggerMessageConfig loggerMessageConfig;

    private final ObjectStateLogger logger;

    public AuthTokenControllerLoggerImpl(
            @NonNull LoggerMessageConfig loggerMessageConfig,
            @NonNull @Qualifier("auth_token_controller_logger") ObjectStateLogger objectStateLogger
    ) {
        this.loggerMessageConfig = loggerMessageConfig;
        this.logger = objectStateLogger;
    }

    @Override
    public void onSuccess(@NonNull Object id) {
        logger.debug(loggerMessageConfig.getSucceedControllerWorkTemplate(), id);
    }

    @Override
    public <T> void onError(@NonNull Object id, @NonNull T objectState, @NonNull Throwable throwable) {
        if (throwable instanceof UsernameNotFoundException) {
            logger.debug(loggerMessageConfig.getUsernameNotFoundTemplate(), id);

        } else if (throwable instanceof WrongPasswordException) {
            logger.debug(loggerMessageConfig.getWrongPassword(), id);

        } else if (throwable instanceof ExpiredAuthTokenException) {
            logger.debug(loggerMessageConfig.getExpiredAuthTokenTemplate(), id);

        } else {
            logger.errorWithObjectState(loggerMessageConfig.getFailedControllerWorkTemplate(), objectState, throwable);
        }
    }
}
