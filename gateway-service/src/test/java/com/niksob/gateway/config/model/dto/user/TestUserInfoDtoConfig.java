package com.niksob.gateway.config.model.dto.user;

import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.dto.user.active.ActiveUserDetailsDto;
import com.niksob.domain_model.dto.user.login.LoginInfoDto;
import com.niksob.gateway.config.model.dto.TestUserValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class TestUserInfoDtoConfig {

    private final LoginInfoDto loginInfoDto = new LoginInfoDto(
            TestUserValues.EMAIL.getValue(),
            TestUserValues.ENCODED_PASSWORD.getValue(),
            Set.of(TestUserValues.USER_ROLE.getValue())
    );

    private final ActiveUserDetailsDto activeDetails = new ActiveUserDetailsDto(
            true,
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    private final RefreshTokenDto refreshTokenDto = new RefreshTokenDto(TestUserValues.REFRESH_TOKEN.getValue());

    @Bean
    public UserInfoDto getUserInfoDto() {
        return new UserInfoDto()
                .setName(TestUserValues.NICKNAME.getValue())
                .setLoginInfo(loginInfoDto)
                .setActiveDetails(activeDetails)
                .setRefreshToken(refreshTokenDto);
    }
}
