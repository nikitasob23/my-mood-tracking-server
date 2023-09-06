package com.niksob.authorization.config.http.client.repo.token.refresh.loading;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.user.login.username.Username;
import jakarta.annotation.PostConstruct;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

@TestConfiguration
@Profile("mocked_http_client")
@ActiveProfiles("mocked_http_client")
public class MockedLoadingRefreshTokenConfig {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private Username username;
    @Autowired
    private RefreshTokenDto refreshTokenDto;
    @Autowired
    @Qualifier("loading_refresh_token_uri")
    private String loadingRefreshTokenUri;

    @PostConstruct
    public void setupMockedMethods() {

        final String urlWithParams = String.format("%s?username=%s", loadingRefreshTokenUri, username.value());
        Mockito.when(httpClient.sendGetRequest(urlWithParams, RefreshTokenDto.class))
                .thenReturn(Mono.just(refreshTokenDto));
    }
}
