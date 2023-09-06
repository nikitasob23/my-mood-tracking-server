package com.niksob.authorization.service.token.validation;

import com.niksob.authorization.converter.token.jwt.TokenToJwtConverter;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.authorization.service.token.loading.refresh.LoadRefreshTokenService;
import com.niksob.domain_model.model.user.login.username.Username;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Service
public class ValidateRefreshTokenServiceImpl implements ValidateRefreshTokenService {

    private final LoadRefreshTokenService loadRefreshTokenService;

    private final JwtProvider jwtProvider;

    private final TokenToJwtConverter tokenToJwtConverter;

    public ValidateRefreshTokenServiceImpl(
            LoadRefreshTokenService loadRefreshTokenService,
            @Qualifier("refresh_jwt_provider") JwtProvider jwtProvider,
            TokenToJwtConverter tokenToJwtConverter
    ) {
        this.loadRefreshTokenService = loadRefreshTokenService;
        this.jwtProvider = jwtProvider;
        this.tokenToJwtConverter = tokenToJwtConverter;
    }

    @Override
    public Boolean execute(RefreshToken refreshToken) {
        return Stream.of(refreshToken)
                .map(tokenToJwtConverter::convert)
                .filter(jwtProvider::validate)
                .filter(jwt -> refreshTokenExists(jwt, refreshToken))
                .map(jwt -> true) // If all filters were passed successfully, return TRUE
                .findFirst().orElse(false);
    }

    private boolean refreshTokenExists(Jwt jwt, RefreshToken refreshToken) {
        return Stream.of(jwt)
                .map(this::getUsernameFromJwt)
                .map(loadRefreshTokenService::execute)
                .map(Mono::block)
                .anyMatch(refreshToken::equals);
    }

    private Username getUsernameFromJwt(Jwt jwt) {
        return Stream.of(jwt)
                .map(jwtProvider::getJwtParams)
                .map(JwtParams::getSubject)
                .map(Username::new)
                .findFirst().get();
    }
}
