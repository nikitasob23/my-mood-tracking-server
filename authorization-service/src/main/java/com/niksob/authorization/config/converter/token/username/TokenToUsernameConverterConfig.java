package com.niksob.authorization.config.converter.token.username;

import com.niksob.authorization.converter.token.jwt.TokenToJwtConverter;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization.converter.token.username.TokenToUsernameConverter;
import com.niksob.authorization.converter.token.username.TokenToUsernameConverterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenToUsernameConverterConfig {

    private final JwtProvider jwtProvider;

    private final TokenToJwtConverter tokenToJwtConverter;

    public TokenToUsernameConverterConfig(
            @Qualifier("refresh_jwt_provider") JwtProvider jwtProvider,
            TokenToJwtConverter tokenToJwtConverter
    ) {
        this.jwtProvider = jwtProvider;
        this.tokenToJwtConverter = tokenToJwtConverter;
    }

    @Bean("get_username_from_refresh_token_service")
    public TokenToUsernameConverter getGetUsernameFromTokenService() {
        return new TokenToUsernameConverterImpl(jwtProvider, tokenToJwtConverter);
    }
}
