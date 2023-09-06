package com.niksob.authorization.config.model.auth.token.jwt;

import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.value.user.dto.AuthTokenValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestJwtConfig {
    @Bean("test_access_jwt")
    public Jwt getTestAccessJwt() {
        return new Jwt(AuthTokenValues.ACCESS_TOKEN.value);
    }

    @Bean("test_refresh_jwt")
    public Jwt getTestRefreshJwt() {
        return new Jwt(AuthTokenValues.REFRESH_TOKEN.value);
    }
}
