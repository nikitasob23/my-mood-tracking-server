package com.niksob.authorization.config.http.client.repo.token.refresh.saving;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.authorization_model.exception.db.refresh_token.save.DatabaseException;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import jakarta.annotation.PostConstruct;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

@TestConfiguration
@Profile("mocked_throwable_saving_refresh_token")
@ActiveProfiles("mocked_http_client")
public class MockedThrowableSaveRefreshTokenConfig {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private RefreshTokenDto refreshTokenDto;
    @Autowired
    @Qualifier("saving_refresh_token_uri")
    private String savingRefreshTokenUri;

    @PostConstruct
    public void setupMockedMethods() {

        Mockito.when(httpClient.sendPostRequest(
                savingRefreshTokenUri,
                refreshTokenDto, RefreshTokenDto.class,
                Void.class
        )).thenReturn(Mono.error(new DatabaseException("Don't save refresh token: TEST_TOKEN")));
    }
}
