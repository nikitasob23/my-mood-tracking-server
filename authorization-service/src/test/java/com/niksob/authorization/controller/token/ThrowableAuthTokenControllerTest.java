package com.niksob.authorization.controller.token;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({
        "mocked_jwt_provider",
        "mocked_password_encoder",
        "mocked_http_client",
        "mocked_throwable_saving_refresh_token"
})
public class ThrowableAuthTokenControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private RowLoginInDetailsDto rowLoginInDetailsDto;

    @Autowired
    @Qualifier("generating_auth_token_by_login_details_uri")
    private String generatingAuthTokenByLoginDetailsUri;

    @Test
    public void testDatabaseExceptionThrowing() {
        webClient.post()
                .uri(generatingAuthTokenByLoginDetailsUri)
                .body(Mono.just(rowLoginInDetailsDto), RowLoginInDetailsDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError()
                .returnResult(AuthTokenDto.class)
                .getResponseBody()
                .blockLast();
    }
}
