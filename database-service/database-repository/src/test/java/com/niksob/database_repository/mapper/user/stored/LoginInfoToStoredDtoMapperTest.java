package com.niksob.database_repository.mapper.user.stored;

import com.niksob.database_repository.mapper.user.stored.login.UserLoginInfoToStoredDtoMapper;
import com.niksob.database_repository.model.user.login.StoredEncodedPasswordDto;
import com.niksob.database_repository.model.user.login.StoredUserLoginInfoDto;
import com.niksob.database_repository.model.user.login.StoredUsernameDto;
import com.niksob.database_repository.model.user.login.role.StoredRoleDto;
import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
import org.mapstruct.factory.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginInfoToStoredDtoMapperTest {

    private final UserLoginInfoToStoredDtoMapper userLoginInfoToStoredDtoMapper =
            Mappers.getMapper(UserLoginInfoToStoredDtoMapper.class);

    private LoginInfo loginInfo;

    private StoredUserLoginInfoDto loginInfoDto;

    @Test
    public void testUserLoginInfoToStoredDtoMapping() {
        final StoredUserLoginInfoDto resultUserInfoDto = userLoginInfoToStoredDtoMapper.toDto(loginInfo);
        assertThat(resultUserInfoDto).isEqualTo(loginInfoDto);
    }

    @Test
    public void testUserLoginInfoFromStoredDtoMapping() {
        final LoginInfo resultUserInfo = userLoginInfoToStoredDtoMapper.fromDto(loginInfoDto);
        assertThat(resultUserInfo).isEqualTo(loginInfo);
    }

    @BeforeEach
    public void prepare() {

        final String usernameStr = "123@gmail.com";
        final String passwordStr = "p";

        loginInfo = new LoginInfo(
                new Username(usernameStr),
                new EncodedPassword(passwordStr),
                Set.of(Role.USER)
        );

        loginInfoDto = new StoredUserLoginInfoDto(
                new StoredUsernameDto(usernameStr),
                new StoredEncodedPasswordDto(passwordStr),
                Set.of(StoredRoleDto.USER)
        );
    }
}
