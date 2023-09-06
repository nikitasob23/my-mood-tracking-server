package com.niksob.authorization.exception.handler.controller;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import reactor.core.publisher.Mono;

public interface ControllerExceptionHandler {
    Mono<AuthTokenDto> execute(Throwable throwable, String path);
}
