package com.niksob.database_model.mapper.login.username;

import com.niksob.domain_model.dto.user.login.username.UsernameDto;
import com.niksob.domain_model.mapper.user.login.username.UsernameDtoMapper;
import com.niksob.domain_model.model.user.login.username.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class UsernameDtoMapperTest {

    private final UsernameDtoMapper usernameDtoMapper = Mappers.getMapper(UsernameDtoMapper.class);

    private Username username;

    private UsernameDto usernameDto;

    @Test
    public void testActiveUserDetailsToDtoMapping() {
        final UsernameDto resultUsernameDto = usernameDtoMapper.toDto(username);
        assertThat(resultUsernameDto).isEqualTo(usernameDto);
    }

    @Test
    public void testActiveUserDetailsFromDtoMapping() {
        final Username resultUsername = usernameDtoMapper.fromDto(usernameDto);
        assertThat(resultUsername).isEqualTo(username);
    }

    @BeforeEach
    public void prepare() {

        final String usernameStr = "123@gmail.com";

        username = new Username(usernameStr);
        usernameDto = new UsernameDto(usernameStr);
    }
}
