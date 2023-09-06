package com.niksob.authorization.config.model.auth.token.jwt;

import com.niksob.authorization.model.jwt.JwtClaims;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.authorization.value.user.dto.UserValues;
import com.niksob.domain_model.model.user.login.role.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class TestJwtParamsConfig {

    @Bean("test_access_jwt_params")
    public JwtParams getTestAccessJwtParams() {
        return new JwtParams(
                UserValues.USERNAME.value,
                new JwtClaims(
                        Map.of(UserValues.ROLES_CLAIM_KEY.value, List.of(UserValues.DEF_ROLE.value))
                ),
                5
        );
    }

    @Bean("test_refresh_jwt_params")
    public JwtParams getTestRefreshJwtParams() {
        return new JwtParams(
                UserValues.USERNAME.value,
                new JwtClaims(
                        Map.of(UserValues.ROLES_CLAIM_KEY.value, List.of(UserValues.DEF_ROLE.value))
                ),
                30
        );
    }
}
