package com.niksob.gateway.config.model.dto.token.update;

import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.gateway.config.model.dto.TestUserValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRefreshTokenDtoConfig {
    @Bean
    public RefreshTokenDto getRefreshTokenDto() {
        return new RefreshTokenDto(TestUserValues.REFRESH_TOKEN.getValue());
    }
}
