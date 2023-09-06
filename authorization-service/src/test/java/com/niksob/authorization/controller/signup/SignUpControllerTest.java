package com.niksob.authorization.controller.signup;

import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"mocked_http_client", "mocked_password_encoder"})
public class SignUpControllerTest {
    @Autowired
    private WebTestClient webClient;
    @Autowired
    private RowUserSignupDetailsDto rowUserSignupDetailsDto;
    @Autowired
    @Qualifier("signup_uri")
    private String signupUri;

    @Test
    public void testSignUp() {
        webClient.post()
                .uri(signupUri)
                .body(Mono.just(rowUserSignupDetailsDto), RowUserSignupDetailsDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}
