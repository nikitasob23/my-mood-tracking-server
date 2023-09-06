package com.niksob.authorization.config.http.client.repo.user.loading;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.authorization.value.user.dto.UserValues;
import com.niksob.domain_model.dto.user.UserInfoDto;
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
public class MockedLoadingUserByUsernameConfig {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private UserInfoDto userInfoDto;
    @Autowired
    @Qualifier("loading_user_by_username_uri")
    private String loadingUserInfoByUsernameUri;

    @PostConstruct
    public void setupMockedMethods() {
        String uriWithParams = String.format("%s?username=%s", loadingUserInfoByUsernameUri, UserValues.USERNAME.value);
        Mockito.when(httpClient.sendGetRequest(uriWithParams, UserInfoDto.class)).thenReturn(Mono.just(userInfoDto));
    }
}
