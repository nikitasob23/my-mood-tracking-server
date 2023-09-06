package com.niksob.authorization.service.token.login;

import com.niksob.authorization.controller.token.AuthTokenGeneratorAsync;
import com.niksob.authorization.service.auth.login.token.LoginInWithTokenServiceAsync;
import com.niksob.authorization.service.auth.token.generation.by_refresh.GenerateAuthTokenByRefreshServiceAsync;
import com.niksob.authorization.service.db.token.refresh.RefreshTokenSavingConnector;
import com.niksob.authorization.service.token.validation.ValidateRefreshTokenService;
import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.exception.auth.token.AuthTokenException;
import com.niksob.authorization_model.mapper.auth.login.RowLoginInDetailsDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.refresh.RefreshTokenDtoMapper;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BaseAuthTokenGeneratorAsync implements AuthTokenGeneratorAsync {
    private final GenerateAuthTokenByRefreshServiceAsync generateAuthTokenByRefreshServiceAsync;
    private final LoginInWithTokenServiceAsync loginInWithTokenServiceAsync;
    private final ValidateRefreshTokenService validateRefreshTokenService;
    private final RefreshTokenSavingConnector refreshTokenSavingConnector;

    private final RefreshTokenDtoMapper refreshTokenDtoMapper;
    private final AuthTokenDtoMapper authTokenDtoMapper;
    private final RowLoginInDetailsDtoMapper rowLoginInDetailsDtoMapper;

    @Override
    public Mono<AuthTokenDto> generateByLoginInDetails(RowLoginInDetailsDto rowLoginInDetailsDto) {
        return Mono.just(rowLoginInDetailsDto)
                .map(rowLoginInDetailsDtoMapper::fromDto)
                .flatMap(loginInWithTokenServiceAsync::execute)
                .flatMap(this::saveRefreshTokenAsync)
                .map(authTokenDtoMapper::toDto);
    }

    @Override
    public Mono<AuthTokenDto> generateByRefreshToken(RefreshTokenDto refreshTokenDto) {

        return Mono.just(refreshTokenDto)
                .map(refreshTokenDtoMapper::fromDto)
                .filter(validateRefreshTokenService::execute)
                .flatMap(generateAuthTokenByRefreshServiceAsync::execute)
                .flatMap(this::saveRefreshTokenAsync)
                .map(authTokenDtoMapper::toDto)
                .switchIfEmpty(Mono.error(AuthTokenException::new));
    }

    private Mono<AuthToken> saveRefreshTokenAsync(@NonNull AuthToken authToken) {
        return refreshTokenSavingConnector.save(authToken.getRefresh())
//                .subscribeOn(Schedulers.parallel())
                .then(Mono.just(authToken));
    }
}
