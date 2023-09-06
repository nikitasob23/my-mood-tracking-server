package com.niksob.authorization.controller.token.exception;

import com.niksob.authorization.config.exception.ExceptionMessagesConfig;
import com.niksob.authorization.exception.handler.controller.ControllerExceptionHandler;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.exception.auth.WrongPasswordException;
import com.niksob.authorization_model.exception.auth.token.ExpiredAuthTokenException;
import com.niksob.authorization_model.exception.db.refresh_token.save.DatabaseException;
import com.niksob.authorization_model.exception.response.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class AuthTokenExceptionHandler implements ControllerExceptionHandler {

    private final ExceptionMessagesConfig exceptionMessagesConfig;

    @Override
    public Mono<AuthTokenDto> execute(Throwable throwable, String path) {

        if (throwable instanceof UsernameNotFoundException) {
            return createMonoError(HttpStatus.NOT_FOUND, exceptionMessagesConfig.getUsernameNotFound(), path);

        } else if (throwable instanceof WrongPasswordException) {
            return createMonoError(HttpStatus.NOT_FOUND, exceptionMessagesConfig.getWrongPassword(), path);

        } else if (throwable instanceof DatabaseException) {
            return createMonoError(HttpStatus.INTERNAL_SERVER_ERROR, exceptionMessagesConfig.getDatabase(), path);

        } else if (throwable instanceof ExpiredAuthTokenException) {
            return createMonoError(HttpStatus.FORBIDDEN, exceptionMessagesConfig.getExpiredAuthToken(), path);

        } else {
            return createMonoError(HttpStatus.INTERNAL_SERVER_ERROR, null, path);

        }
    }

    private Mono<AuthTokenDto> createMonoError(HttpStatus httpStatus, String exceptionMessage, String path) {
        return Mono.error(new ResponseStatusException(httpStatus, exceptionMessage, LocalDateTime.now(), path));
    }
}
