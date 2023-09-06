package com.niksob.authorization.config.model.auth.token;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.model.token.access.AccessToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAuthTokenDtoConfig {

    @Autowired
    private AccessToken accessToken;

    @Autowired
    private RefreshToken refreshToken;

    @Bean
    public AuthTokenDto getTestAuthTokenDto() {
        return new AuthTokenDto(accessToken.getValue(), refreshToken.getValue());
    }
}
