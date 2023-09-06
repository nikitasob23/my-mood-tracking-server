package com.niksob.authorization.config.http.client;

import com.niksob.authorization.http.client.HttpClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("mocked_http_client")
public class MockedHttpClientConfig {
    @MockBean
    private HttpClient httpClient;
}
