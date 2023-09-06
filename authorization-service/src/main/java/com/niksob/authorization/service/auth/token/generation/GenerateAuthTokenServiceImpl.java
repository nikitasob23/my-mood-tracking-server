package com.niksob.authorization.service.auth.token.generation;

import com.niksob.authorization.converter.jwt.access.JwtToAccessTokenConverter;
import com.niksob.authorization.converter.jwt.refresh.JwtToRefreshTokenConverter;
import com.niksob.authorization.converter.user.jwt.UserToJwtParamsConverter;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization_model.model.token.access.AccessToken;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.model.user.UserInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class GenerateAuthTokenServiceImpl implements GenerateAuthTokenService {

    @NonNull
    private final JwtProvider accessJwtProvider;

    @NonNull
    private final JwtProvider refreshJwtProvider;

    @NonNull
    private final UserToJwtParamsConverter userToAccessJwtParamsConverter;

    @NonNull
    private final UserToJwtParamsConverter userToRefreshJwtParamsConverter;

    @NonNull
    private final JwtToAccessTokenConverter jwtToAccessTokenConverter;

    @NonNull
    private final JwtToRefreshTokenConverter jwtToRefreshTokenConverter;

    public GenerateAuthTokenServiceImpl(
            @NonNull @Qualifier("access_jwt_provider") JwtProvider accessJwtProvider,
            @NonNull @Qualifier("refresh_jwt_provider") JwtProvider refreshJwtProvider,
            @NonNull @Qualifier("access_jwt_params_converter") UserToJwtParamsConverter userToAccessJwtParamsConverter,
            @NonNull @Qualifier("refresh_jwt_params_converter") UserToJwtParamsConverter userToRefreshJwtParamsConverter,
            @NonNull JwtToAccessTokenConverter jwtToAccessTokenConverter,
            @NonNull JwtToRefreshTokenConverter jwtToRefreshTokenConverter
    ) {
        this.accessJwtProvider = accessJwtProvider;
        this.refreshJwtProvider = refreshJwtProvider;
        this.userToAccessJwtParamsConverter = userToAccessJwtParamsConverter;
        this.userToRefreshJwtParamsConverter = userToRefreshJwtParamsConverter;
        this.jwtToAccessTokenConverter = jwtToAccessTokenConverter;
        this.jwtToRefreshTokenConverter = jwtToRefreshTokenConverter;
    }

    @Override
    public AuthToken execute(@NonNull UserInfo userInfo) {

        final AccessToken accessToken = Stream.of(userInfo)
                .map(userToAccessJwtParamsConverter::convert)
                .map(accessJwtProvider::generate)
                .map(jwtToAccessTokenConverter::convert)
                .findFirst().get();

        final RefreshToken refreshToken = Stream.of(userInfo)
                .map(userToRefreshJwtParamsConverter::convert)
                .map(refreshJwtProvider::generate)
                .map(jwtToRefreshTokenConverter::convert)
                .findFirst().get();

        return new AuthToken(accessToken, refreshToken);
    }
}
