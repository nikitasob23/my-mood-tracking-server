package com.niksob.authorization.config.service.user.creation;

import com.niksob.authorization.service.user.creation.CreateDefUserByLoginDetailsService;
import com.niksob.authorization.service.user.creation.CreateDefUserByLoginDetailsServiceImpl;
import com.niksob.authorization_model.mapper.auth.login.SignupDetailsMapper;
import com.niksob.authorization_model.util.date.ActiveUserDetailsDateTimeUtil;
import com.niksob.domain_model.model.user.login.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class CreateDefUserByLoginDetailsServiceConfig {

    private static final Set<Role> DEF_ROLES = Set.of(Role.USER);
    private final ActiveUserDetailsDateTimeUtil activeUserDetailsDateTimeUtil;
    private final SignupDetailsMapper signupDetailsMapper;

    @Primary
    @Bean
    public CreateDefUserByLoginDetailsService getCreateDefUserByLoginDetailsService() {
        return new CreateDefUserByLoginDetailsServiceImpl(
                DEF_ROLES,
                activeUserDetailsDateTimeUtil,
                signupDetailsMapper
        );
    }
}
