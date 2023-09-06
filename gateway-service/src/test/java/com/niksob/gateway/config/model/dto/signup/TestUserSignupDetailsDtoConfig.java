package com.niksob.gateway.config.model.dto.signup;

import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.gateway.config.model.dto.TestUserValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestUserSignupDetailsDtoConfig {
    @Bean
    public RowUserSignupDetailsDto getLoginDetailsDto() {
        return new RowUserSignupDetailsDto(
                TestUserValues.EMAIL.getValue(),
                TestUserValues.NICKNAME.getValue(),
                TestUserValues.ROW_PASSWORD.getValue()
        );
    }
}
