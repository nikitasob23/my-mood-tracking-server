package com.niksob.authorization.controller.token;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"mocked_jwt_provider", "mocked_password_encoder", "mocked_http_client", "mocked_saving_refresh_token"})
public class AuthTokenControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private RowLoginInDetailsDto rowLoginInDetailsDto;
    @Autowired
    private RefreshTokenDto refreshTokenDto;
    @Autowired
    private AuthTokenDto expectedAuthTokenDto;

    @Autowired
    @Qualifier("generating_auth_token_by_login_details_uri")
    private String generatingAuthTokenByLoginDetailsUri;
    @Autowired
    @Qualifier("generating_auth_token_by_refresh_uri")
    private String generatingAuthTokenByRefreshUri;

    @Test
    public void testGenerationByLoginDetails() {
        final AuthTokenDto authToken = webClient.post()
                .uri(generatingAuthTokenByLoginDetailsUri)
                .body(Mono.just(rowLoginInDetailsDto), RowLoginInDetailsDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(AuthTokenDto.class)
                .getResponseBody()
                .blockLast();

        assertThat(authToken).isEqualTo(expectedAuthTokenDto);
    }

    @Test
    public void testGenerationByRefreshToken() {
        final AuthTokenDto authToken = webClient.post()
                .uri(generatingAuthTokenByRefreshUri)
                .body(Mono.just(refreshTokenDto), RefreshTokenDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(AuthTokenDto.class)
                .getResponseBody()
                .blockLast();

        assertThat(authToken).isEqualTo(expectedAuthTokenDto);
    }
}
