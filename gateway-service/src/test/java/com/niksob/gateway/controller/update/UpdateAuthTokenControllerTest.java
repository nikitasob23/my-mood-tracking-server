package com.niksob.gateway.controller.update;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.gateway.client.HttpClient;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateAuthTokenControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    @Qualifier("update_auth_token_controller_uri")
    private String updateAuthTokenControllerUri;

    @Autowired
    @Qualifier("generating_auth_token_by_refresh_uri")
    private String generatingByRefreshTokenUri;

    @Autowired
    private RefreshTokenDto testRefreshTokenDto;

    @Autowired
    private AuthTokenDto testAuthTokenDto;

    @MockBean
    private HttpClient httpClient;

    @Test
    public void testUpdatingAuthToken() {

        final AuthTokenDto authTokenDto = webClient.post()
                .uri(updateAuthTokenControllerUri)
                .body(Mono.just(testRefreshTokenDto), RefreshTokenDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(AuthTokenDto.class)
                .getResponseBody()
                .blockLast();

        assertThat(authTokenDto).isEqualTo(testAuthTokenDto);
    }

    @BeforeEach
    public void prepare() {
        mockGeneratingByRefreshToken();
    }

    private void mockGeneratingByRefreshToken() {

        Mockito.when(httpClient.sendPostRequest(
                generatingByRefreshTokenUri,
                testRefreshTokenDto, RefreshTokenDto.class,
                AuthTokenDto.class
        )).thenReturn(Mono.just(testAuthTokenDto));
    }
}

