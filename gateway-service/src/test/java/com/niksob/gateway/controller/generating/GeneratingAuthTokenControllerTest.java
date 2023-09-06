package com.niksob.gateway.controller.generating;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.gateway.client.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeneratingAuthTokenControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private AuthTokenDto testAuthTokenDto;

    @Autowired
    private RowLoginInDetailsDto testRowLoginInDetailsDto;

    @Autowired
    private UserInfoDto testUserInfoDto;

    @Autowired
    @Qualifier("generating_auth_token_by_user_details")
    private String generatingAuthTokenByUserDetails;

    @MockBean
    private HttpClient httpClient;

    @Test
    public void testGeneration() {
        final AuthTokenDto authTokenDto = webClient.post()
                .uri(generatingAuthTokenByUserDetails)
                .body(Mono.just(testRowLoginInDetailsDto), RowUserSignupDetailsDto.class)
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

        mockUserInfoLoading();
    }

    private void mockUserInfoLoading() {
//        final String uriWithParams =
//                String.format("%s/%s", loadingUserByUsernameUri, testRowLoginInDetailsDto.getUsername());
//
//        Mockito.when()
    }
}
