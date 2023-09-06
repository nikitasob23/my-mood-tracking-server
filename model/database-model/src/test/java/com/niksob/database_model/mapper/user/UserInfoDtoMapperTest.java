package com.niksob.database_model.mapper.user;

import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.dto.user.active.ActiveUserDetailsDto;
import com.niksob.domain_model.dto.user.login.LoginInfoDto;
import com.niksob.domain_model.mapper.user.UserInfoDtoMapper;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserInfoDtoMapperTest {

    @Autowired
    private UserInfoDtoMapper userInfoDtoMapper;

    private UserInfo userInfo;

    private UserInfoDto userInfoDto;

    @Test
    public void testActiveUserDetailsToDtoMapping() {
        final UserInfoDto resultUserInfoDto = userInfoDtoMapper.toDto(userInfo);
        assertThat(resultUserInfoDto).isEqualTo(userInfoDto);
    }

    @Test
    public void testActiveUserDetailsFromDtoMapping() {
        final UserInfo resultUserInfo = userInfoDtoMapper.fromDto(userInfoDto);
        assertThat(resultUserInfo).isEqualTo(userInfo);
    }

    @BeforeEach
    public void prepare() {

        final String name = "u";
        final String usernameStr = "123@gmail.com";
        final String passwordStr = "p";
        final Set<String> rolesStr = Set.of("USER");

        final boolean activate = true;
        boolean nonLocked = false;
        final LocalDateTime creation = LocalDateTime.now().minusDays(3);
        LocalDateTime lastVisit = LocalDateTime.now();

        final String refreshTokenStr = "1234567890";

        userInfoDto = new UserInfoDto(
                name,
                new LoginInfoDto(usernameStr, passwordStr, rolesStr),
                new ActiveUserDetailsDto(activate, nonLocked, creation, lastVisit),
                new RefreshTokenDto(refreshTokenStr)
        );

        userInfo = new UserInfo(
                name,
                new LoginInfo(
                        new Username(usernameStr),
                        new EncodedPassword(passwordStr),
                        rolesStr.stream()
                                .map(Role::valueOf)
                                .collect(Collectors.toSet())
                ),
                new ActiveUserDetails(activate, nonLocked, creation, lastVisit),
                new RefreshToken(refreshTokenStr)
        );
    }
}
