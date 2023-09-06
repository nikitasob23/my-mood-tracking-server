package com.niksob.gateway.config.model.dto.token;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.gateway.config.model.dto.TestUserValues;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class TestAuthTokenDtoConfig {

    private final RefreshTokenDto refreshTokenDto;

    @Bean
    public AuthTokenDto getAuthTokenDto() {
        return new AuthTokenDto(
                TestUserValues.ACCESS_TOKEN.getValue(),
                refreshTokenDto.getValue()
        );
    }
}
