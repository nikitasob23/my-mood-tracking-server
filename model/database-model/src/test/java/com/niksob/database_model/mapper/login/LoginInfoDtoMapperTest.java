package com.niksob.database_model.mapper.login;

import com.niksob.domain_model.dto.user.login.LoginInfoDto;
import com.niksob.domain_model.mapper.user.login.UserLoginInfoDtoMapper;
import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginInfoDtoMapperTest {

    private final UserLoginInfoDtoMapper userLoginInfoDtoMapper = Mappers.getMapper(UserLoginInfoDtoMapper.class);

    @Test
    public void testUserLoginInfoMapping() {

        final String usernameStr = "u";
        final String passwordStr = "p";
        final LoginInfo expectedLoginInfo = new LoginInfo(
                new Username(usernameStr),
                new EncodedPassword(passwordStr),
                Set.of(Role.USER)
        );

        final LoginInfoDto loginInfoDto = new LoginInfoDto(
                usernameStr,
                passwordStr,
                Set.of("USER")
        );

        final LoginInfo resultLoginInfo = userLoginInfoDtoMapper.fromDto(loginInfoDto);

        assertThat(resultLoginInfo).isEqualTo(expectedLoginInfo);
    }
}
