package com.niksob.authorization.config.converter.user.jwt;

import com.niksob.authorization.converter.user.jwt.UserToJwtParamsConverter;
import com.niksob.authorization.converter.user.jwt.UserToJwtParamsConverterImpl;
import com.niksob.authorization.values.user.RoleKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserToAccessJwtParamsConverterConfig {
    private final RoleKey roleKey = RoleKey.ROLES_KEY;

    @Value("${auth.jwt.access.expiration-in-minutes}")
    private int expirationInMinutes;

    @Bean("access_jwt_params_converter")
    public UserToJwtParamsConverter getTokenLoginInfoToJwtParamsConverter() {
        return new UserToJwtParamsConverterImpl(roleKey, expirationInMinutes);
    }
}
