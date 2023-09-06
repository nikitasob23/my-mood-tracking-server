package com.niksob.gateway.controller.signup;

import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization_model.dto.signup.SignupDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.exception.rest.status.HttpStatusException;
import com.niksob.gateway.client.HttpClient;
import com.niksob.authorization_model.dto.token.details.AuthTokenDetailsDto;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mocked_http_client")
public class SignupControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private SignupDetailsDto testSignupDetailsDto;

    @Autowired
    private AuthTokenDetailsDto testAuthTokenDetailsDto;

    @Autowired
    private AuthTokenDto testAuthTokenDto;

    @Autowired
    private RowUserSignupDetailsDto testRowUserSignupDetailsDto;

    @Autowired
    @Qualifier("gateway_signup_uri")
    private String gatewaySignupUri;

    @Autowired
    @Qualifier("generating_auth_token_by_login_details_uri")
    private String generatingAuthTokenUri;

    @MockBean
    private HttpClient httpClient;

    @Test
    public void signupTest() {
        final AuthTokenDto authTokenDto = webClient.post()
                .uri(gatewaySignupUri)
                .body(Mono.just(testRowUserSignupDetailsDto), RowUserSignupDetailsDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(AuthTokenDto.class)
                .getResponseBody()
                .blockLast();

        assertThat(authTokenDto).isEqualTo(testAuthTokenDto);
    }

    @Test
    public void signupTest2() {
        final AuthTokenDto authTokenDto = webClient.post()
                .uri(gatewaySignupUri)
                .body(Mono.just(testRowUserSignupDetailsDto), RowUserSignupDetailsDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .returnResult(AuthTokenDto.class)
                .getResponseBody()
                .onErrorResume(throwable -> {
                    final HttpStatusException httpStatusException = (HttpStatusException) throwable;
                    assertThat(httpStatusException.getHttpStatus())
                            .as(httpStatusException.getMessage())
                            .isEqualTo(HttpStatus.OK);
                    return Mono.error(throwable);
                })
                .blockLast();

        assertThat(authTokenDto).isEqualTo(testAuthTokenDto);
    }

    @BeforeEach
    public void prepare() {
        mockAuthTokenGenerationHttpClient();
    }

    private void mockAuthTokenGenerationHttpClient() {

        Mockito.when(httpClient.sendPostRequest(
                generatingAuthTokenUri,
                testAuthTokenDetailsDto, AuthTokenDetailsDto.class,
                AuthTokenDto.class
        )).thenReturn(Mono.just(testAuthTokenDto));
    }
}
