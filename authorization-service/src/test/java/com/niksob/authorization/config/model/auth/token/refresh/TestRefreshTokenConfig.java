package com.niksob.authorization.config.model.auth.token.refresh;

import com.niksob.authorization.value.user.dto.AuthTokenValues;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRefreshTokenConfig {

//    private final RefreshToken refreshToken;
//    private final RefreshTokenDto refreshTokenDto;
//
//    public TestRefreshTokenConfig(
//            @Qualifier("refresh_jwt_provider") JwtProvider jwtProvider,
//            @Qualifier("test_refresh_jwt_params") JwtParams jwtParams,
//            JwtToRefreshTokenConverter jwtToRefreshTokenConverter,
//            RefreshTokenDtoMapper refreshTokenDtoMapper
//    ) {
//        Jwt jwt = jwtProvider.generate(jwtParams);
//
//        this.refreshToken = jwtToRefreshTokenConverter.convert(jwt);
//        this.refreshTokenDto = refreshTokenDtoMapper.toDto(this.refreshToken);
//    }

    @Bean
    public RefreshToken getTestRefreshToken() {
        return new RefreshToken(AuthTokenValues.REFRESH_TOKEN.value);
    }

    @Bean
    public RefreshTokenDto getTestRefreshTokenDto() {
        return new RefreshTokenDto(AuthTokenValues.REFRESH_TOKEN.value);
    }
}
