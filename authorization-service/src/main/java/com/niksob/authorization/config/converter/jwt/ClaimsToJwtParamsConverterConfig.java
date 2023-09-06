package com.niksob.authorization.config.converter.jwt;

import com.niksob.authorization.converter.jwt.jwt_params.claims.ClaimsToJwtParamsConverter;
import com.niksob.authorization.converter.jwt.jwt_params.claims.ClaimsToJwtParamsConverterImpl;
import com.niksob.authorization.values.jwt.JwtKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClaimsToJwtParamsConverterConfig {

    private final JwtKey subjectJwtKey = JwtKey.SUBJECT_KEY;

    private final JwtKey expirationJwtKey = JwtKey.EXPIRATION_KEY;

    @Bean
    public ClaimsToJwtParamsConverter getClaimsToJwtParamsConverter() {
        return new ClaimsToJwtParamsConverterImpl(subjectJwtKey, expirationJwtKey);
    }
}
