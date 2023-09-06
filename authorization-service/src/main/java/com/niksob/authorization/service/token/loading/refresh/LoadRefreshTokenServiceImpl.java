package com.niksob.authorization.service.token.loading.refresh;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.authorization.converter.token.refresh.RefreshTokenDtoConverter;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.model.user.login.username.Username;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoadRefreshTokenServiceImpl implements LoadRefreshTokenService {

    private final HttpClient httpClient;

    private final RefreshTokenDtoConverter refreshTokenDtoConverter;

    private final String loadingRefreshTokenUri;

    public LoadRefreshTokenServiceImpl(
            HttpClient httpClient,
            RefreshTokenDtoConverter refreshTokenDtoConverter,
            @Qualifier("loading_refresh_token_uri") String loadingRefreshTokenUri
    ) {
        this.httpClient = httpClient;
        this.refreshTokenDtoConverter = refreshTokenDtoConverter;
        this.loadingRefreshTokenUri = loadingRefreshTokenUri;
    }

    @Override
    public Mono<RefreshToken> execute(Username username) {

        final String urlWithParams = String.format("%s?username=%s", loadingRefreshTokenUri, username.value());
        return httpClient.sendGetRequest(urlWithParams, RefreshTokenDto.class)
                .map(refreshTokenDtoConverter::convert);
    }
}
