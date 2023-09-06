package com.niksob.gateway.config.model.dto.signup;

import com.niksob.authorization_model.dto.signup.SignupDetailsDto;
import com.niksob.gateway.config.model.dto.TestUserValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class TestSignupInfoDtoConfig {
    @Bean
    public SignupDetailsDto getSignupInfoDto() {
        return new SignupDetailsDto(
                TestUserValues.EMAIL.getValue(),
                TestUserValues.NICKNAME.getValue(),
                TestUserValues.ROW_PASSWORD.getValue(),
                Set.of(TestUserValues.USER_ROLE.getValue())
        );
    }
}
