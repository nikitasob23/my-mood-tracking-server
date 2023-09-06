package com.niksob.authorization.controller.token;

import com.niksob.authorization.exception.handler.controller.ControllerExceptionHandler;
import com.niksob.authorization.logger.controller.token.AuthTokenControllerLogger;
import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.rest.path.controller.token.AuthTokenControllerPaths;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(AuthTokenControllerPaths.BASE_URI)
@AllArgsConstructor
public class AuthTokenController implements AuthTokenGeneratorAsync {
    public static final String GENERATION_BY_DETAILS_PATH =
            AuthTokenControllerPaths.BASE_URI + AuthTokenControllerPaths.GENERATION;
    public static final String GENERATION_BY_REFRESH_PATH =
            AuthTokenControllerPaths.BASE_URI + AuthTokenControllerPaths.GENERATION_REFRESH;

    @NonNull
    @Qualifier("base_auth_token_generator_async")
    private final AuthTokenGeneratorAsync authTokenGeneratorServiceAsync;

    @NonNull
    private final AuthTokenControllerLogger logger;

    @NonNull
    @Qualifier("auth_token_exception_handler")
    private final ControllerExceptionHandler controllerExceptionHandler;

    @PostMapping(AuthTokenControllerPaths.GENERATION)
    @Override
    public Mono<AuthTokenDto> generateByLoginInDetails(@RequestBody RowLoginInDetailsDto rowLoginInDetailsDto) {
        return Mono.just(rowLoginInDetailsDto)
                .flatMap(authTokenGeneratorServiceAsync::generateByLoginInDetails)
                .doOnSuccess(authTokenDto -> logger.onSuccess(rowLoginInDetailsDto))
                .doOnError(throwable ->
                        logger.onError(rowLoginInDetailsDto.getUsername(), rowLoginInDetailsDto, throwable)
                )
                .onErrorResume(throwable -> controllerExceptionHandler.execute(throwable, GENERATION_BY_DETAILS_PATH));
    }

    @PostMapping(AuthTokenControllerPaths.GENERATION_REFRESH)
    @Override
    public Mono<AuthTokenDto> generateByRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return Mono.just(refreshTokenDto)
                .flatMap(authTokenGeneratorServiceAsync::generateByRefreshToken)
                .doOnSuccess(authTokenDto -> logger.onSuccess(refreshTokenDto))
                .doOnError(throwable ->
                        logger.onError(refreshTokenDto.getValue(), refreshTokenDto, throwable)
                )
                .onErrorResume(throwable -> controllerExceptionHandler.execute(throwable, GENERATION_BY_REFRESH_PATH));
    }
}
