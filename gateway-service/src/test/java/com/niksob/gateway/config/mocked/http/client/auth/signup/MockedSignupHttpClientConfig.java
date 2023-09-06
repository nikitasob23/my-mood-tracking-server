package com.niksob.gateway.config.mocked.http.client.auth.signup;

import com.niksob.authorization_model.dto.signup.SignupDetailsDto;
import com.niksob.gateway.client.HttpClient;
import jakarta.annotation.PostConstruct;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

@TestConfiguration
@Profile("mocked_http_client")
@ActiveProfiles("mocked_http_client")
public class MockedSignupHttpClientConfig {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    @Qualifier("gateway_signup_uri")
    private String authServiceSignupUri;
    @Autowired
    private SignupDetailsDto testSignupDetailsDto;

    @PostConstruct
    public void setupMockedMethods() {
        final Mono<Void> emptyMono = Mono.empty();

        Mockito.when(httpClient.sendPostRequest(
                authServiceSignupUri,
                testSignupDetailsDto, SignupDetailsDto.class
        )).thenReturn(emptyMono);
    }
}
