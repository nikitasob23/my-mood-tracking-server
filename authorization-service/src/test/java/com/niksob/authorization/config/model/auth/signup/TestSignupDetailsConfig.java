package com.niksob.authorization.config.model.auth.signup;

import com.niksob.authorization.value.user.dto.UserValues;
import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class TestSignupDetailsConfig {

    @Autowired
    private Username username;
    @Autowired
    @Qualifier("test_nickname")
    private String nickname;
    @Autowired
    private EncodedPassword encodedPassword;
    @Autowired
    private RowPassword rowPassword;
    @Autowired
    @Qualifier("test_roles")
    private Set<Role> roles;

    @Bean
    public SignupDetails getTestSignupDetails() {
        return new SignupDetails(
                username,
                nickname,
                encodedPassword,
                roles
        );
    }

    @Bean
    public RowUserSignupDetails getTestUserRowSignupDetails() {
        return new RowUserSignupDetails(username, nickname, rowPassword);
    }

    @Bean
    public RowUserSignupDetailsDto getTestUserSignupDetailsDto() {
        return new RowUserSignupDetailsDto(
                UserValues.USERNAME.value,
                UserValues.NICKNAME.value,
                UserValues.ROW_PASSWORD.value
        );
    }
}
