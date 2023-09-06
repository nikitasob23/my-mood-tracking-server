package com.niksob.authorization.config.model.user;

import com.niksob.authorization.value.user.dto.UserValues;
import com.niksob.authorization_model.util.date.ActiveUserDetailsDateTimeUtil;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.mapper.user.UserInfoDtoMapper;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
import org.mockito.Mockito;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.Set;

@TestConfiguration
public class TestUserConfig {

    @Autowired
    private RefreshToken refreshToken;
    @Autowired
    private UserInfoDtoMapper userInfoDtoMapper;

    @MockBean
    private ActiveUserDetailsDateTimeUtil activeUserDetailsDateTimeUtil;

    @PostConstruct
    private void mockActiveUserDetailsDateTimeUtil() {
        Mockito.when(activeUserDetailsDateTimeUtil.current()).thenReturn(LocalDateTime.now());
    }

    @Bean
    public Username getUsername() {
        return new Username(UserValues.USERNAME.value);
    }

    @Bean("test_nickname")
    public String getNickname() {
        return UserValues.NICKNAME.value;
    }

    @Bean
    public RowPassword getRowPassword() {
        return new RowPassword(UserValues.ROW_PASSWORD.value);
    }

    @Bean
    public EncodedPassword getEncodedPassword() {
        return new EncodedPassword(UserValues.ENCODED_PASSWORD.value);
    }

    @Bean("test_roles")
    public Set<Role> getTestRoles() {
        return Set.of(
                Role.valueOf(UserValues.DEF_ROLE.value)
        );
    }

    @Bean
    @Primary
    public UserInfo getTestUserInfo() {
        return new UserInfo()
                .setName(UserValues.NICKNAME.value)
                .setLoginInfo(getTestLoginInfoDto())
                .setActiveDetails(getTestActiveUserDetailsDto())
                .setRefreshToken(refreshToken);
    }

    @Bean("test_user_info_without_refresh_token")
    public UserInfo getTestUserInfoWithoutRefreshToken() {
        final UserInfo testUserInfo = getTestUserInfo();
        testUserInfo.setRefreshToken(null);
        return testUserInfo;
    }

    @Bean
    @Primary
    public UserInfoDto getTestUserInfoDto() {
        return userInfoDtoMapper.toDto(getTestUserInfo());
    }

    @Bean("test_user_info_dto_without_refresh_token")
    public UserInfoDto getTestUserInfoDtoWithoutRefreshToken() {
        final UserInfoDto testUserInfo = getTestUserInfoDto();
        testUserInfo.setRefreshToken(null);
        return testUserInfo;
    }

    @Bean
    public LoginInfo getTestLoginInfoDto() {
        return new LoginInfo(
                getUsername(),
                new EncodedPassword(UserValues.ENCODED_PASSWORD.value),
                Set.of(Role.valueOf(UserValues.DEF_ROLE.value))
        );
    }

    @Bean
    public ActiveUserDetails getTestActiveUserDetailsDto() {
        return new ActiveUserDetails()
                .setActive(true)
                .setNonLocked(true)
                .setCreationDateTime(activeUserDetailsDateTimeUtil.current())
                .setLastVisitDateTime(activeUserDetailsDateTimeUtil.current());
    }
}
