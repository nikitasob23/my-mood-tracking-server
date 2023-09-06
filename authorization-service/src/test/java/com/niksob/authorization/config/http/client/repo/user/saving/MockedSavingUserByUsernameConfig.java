package com.niksob.authorization.config.http.client.repo.user.saving;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.domain_model.dto.user.UserInfoDto;
import org.mockito.Mockito;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

@TestConfiguration
@Profile("mocked_http_client")
@ActiveProfiles("mocked_http_client")
public class MockedSavingUserByUsernameConfig {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    @Qualifier("test_user_info_dto_without_refresh_token")
    private UserInfoDto userInfoDto;
    @Autowired
    @Qualifier("saving_user_uri")
    private String savingUserUri;

    @PostConstruct
    public void setupMockedMethods() {

        Mockito.when(httpClient.sendPostRequest(
                savingUserUri,
                userInfoDto, UserInfoDto.class,
                Void.class
        )).thenReturn(Mono.empty());
    }
}
