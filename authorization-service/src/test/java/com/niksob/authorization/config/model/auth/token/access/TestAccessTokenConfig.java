package com.niksob.authorization.config.model.auth.token.access;

import com.niksob.authorization.value.user.dto.AuthTokenValues;
import com.niksob.authorization_model.dto.token.access.AccessTokenDto;
import com.niksob.authorization_model.model.token.access.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAccessTokenConfig {

    @Bean
    public AccessToken getTestAccessToken() {
        return new AccessToken(AuthTokenValues.ACCESS_TOKEN.value);
    }

    @Bean
    public AccessTokenDto getTestAccessTokenDto() {
        return new AccessTokenDto(AuthTokenValues.ACCESS_TOKEN.value);
    }
}
