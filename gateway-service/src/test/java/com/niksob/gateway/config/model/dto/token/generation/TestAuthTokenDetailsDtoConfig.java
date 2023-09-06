package com.niksob.gateway.config.model.dto.token.generation;

import com.niksob.gateway.config.model.dto.TestUserValues;
import com.niksob.authorization_model.dto.token.details.AuthTokenDetailsDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class TestAuthTokenDetailsDtoConfig {
    @Bean
    public AuthTokenDetailsDto getAuthTokenDetailsDto() {
        return new AuthTokenDetailsDto(
                TestUserValues.EMAIL.getValue(),
                TestUserValues.ROW_PASSWORD.getValue(),
                Set.of(TestUserValues.USER_ROLE.getValue())
        );
    }
}
