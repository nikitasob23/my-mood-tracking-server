package com.niksob.gateway.service.auth.token.update;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.refresh.RefreshTokenDtoMapper;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.gateway.client.HttpClient;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateAuthTokenServiceImpl implements UpdateAuthTokenService {

    private final HttpClient httpClient;

    private final RefreshTokenDtoMapper refreshTokenDtoMapper;

    private final AuthTokenDtoMapper authTokenDtoMapper;

    private final String generatingAuthTokenByRefreshUri;

    public UpdateAuthTokenServiceImpl(
            @NonNull HttpClient httpClient,
            @NonNull RefreshTokenDtoMapper refreshTokenDtoMapper,
            @NonNull AuthTokenDtoMapper authTokenDtoMapper,
            @NonNull @Qualifier("generating_auth_token_by_refresh_uri") String generatingAuthTokenByRefreshUri
    ) {
        this.httpClient = httpClient;
        this.refreshTokenDtoMapper = refreshTokenDtoMapper;
        this.authTokenDtoMapper = authTokenDtoMapper;
        this.generatingAuthTokenByRefreshUri = generatingAuthTokenByRefreshUri;
    }

    @Override
    public Mono<AuthToken> execute(RefreshToken refreshToken) {

        final RefreshTokenDto refreshTokenDto = refreshTokenDtoMapper.toDto(refreshToken);

        return httpClient.sendPostRequest(
                        generatingAuthTokenByRefreshUri,
                        refreshTokenDto, RefreshTokenDto.class,
                        AuthTokenDto.class
                )
                .map(authTokenDtoMapper::fromDto);
    }
}
