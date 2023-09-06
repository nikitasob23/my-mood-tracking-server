package com.niksob.gateway.service.auth.token;

import com.niksob.gateway.client.HttpClient;
import com.niksob.authorization_model.model.login.UserLoginInDetails;
import com.niksob.authorization_model.model.token.AuthToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoginInWithTokenServiceImpl implements LoginInWithTokenService {

    private final HttpClient httpClient;

//    @Value("")

    @Override
    public Mono<AuthToken> execute(UserLoginInDetails userLoginInDetails) {

//        httpClient.sendPostRequest()

        return null;
    }
}
