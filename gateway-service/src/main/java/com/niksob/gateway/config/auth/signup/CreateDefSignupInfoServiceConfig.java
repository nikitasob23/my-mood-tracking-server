package com.niksob.gateway.config.auth.signup;

import com.niksob.gateway.service.auth.signup.creation.CreateDefSignupInfoService;
import com.niksob.gateway.service.auth.signup.creation.CreateDefSignupInfoServiceImpl;
import com.niksob.domain_model.model.user.login.role.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Configuration
public class CreateDefSignupInfoServiceConfig {

    private final Set<Role> defRoles = Set.of(Role.USER);

    @Primary
    @Bean
    public CreateDefSignupInfoService getCreateDefSignupInfoService() {
        return new CreateDefSignupInfoServiceImpl(defRoles);
    }
}
