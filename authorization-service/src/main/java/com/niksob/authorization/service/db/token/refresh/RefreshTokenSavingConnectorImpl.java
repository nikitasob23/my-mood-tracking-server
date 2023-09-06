package com.niksob.authorization.service.db.token.refresh;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.authorization_model.mapper.auth.token.refresh.RefreshTokenDtoMapper;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RefreshTokenSavingConnectorImpl implements RefreshTokenSavingConnector {
    private final HttpClient httpClient;
    private final RefreshTokenDtoMapper refreshTokenDtoMapper;
    private final String savingRefreshTokenUri;

    public RefreshTokenSavingConnectorImpl(
            HttpClient httpClient,
            RefreshTokenDtoMapper refreshTokenDtoMapper,
            @Qualifier("saving_refresh_token_uri") String savingRefreshTokenUri
    ) {
        this.httpClient = httpClient;
        this.refreshTokenDtoMapper = refreshTokenDtoMapper;
        this.savingRefreshTokenUri = savingRefreshTokenUri;
    }

    @Override
    public Mono<Void> save(RefreshToken refreshToken) {

        return httpClient.sendPostRequest(
                savingRefreshTokenUri,
                refreshTokenDtoMapper.toDto(refreshToken), RefreshTokenDto.class,
                Void.class
        );
    }
}
