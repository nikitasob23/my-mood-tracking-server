package com.niksob.gateway.config.model.dto.login;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.gateway.config.model.dto.TestUserValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRowLoginInDetailsDtoConfig {
    @Bean
    public RowLoginInDetailsDto getRowLoginInDetailsDto() {
        return new RowLoginInDetailsDto(
                TestUserValues.EMAIL.getValue(),
                TestUserValues.ROW_PASSWORD.getValue()
        );
    }
}
