package com.niksob.authorization.config.service.token.login;

import com.niksob.authorization.service.auth.login.token.LoginInWithTokenServiceAsync;
import com.niksob.authorization.service.auth.token.generation.by_refresh.GenerateAuthTokenByRefreshServiceAsync;
import com.niksob.authorization.service.db.token.refresh.RefreshTokenSavingConnector;
import com.niksob.authorization.service.token.login.BaseAuthTokenGeneratorAsync;
import com.niksob.authorization.service.token.validation.ValidateRefreshTokenService;
import com.niksob.authorization_model.mapper.auth.login.RowLoginInDetailsDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.refresh.RefreshTokenDtoMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class BaseAuthTokenGeneratorAsyncConfig {
    @Bean("base_auth_token_generator_async")
    public BaseAuthTokenGeneratorAsync getBaseAuthTokenGeneratorAsync(
            GenerateAuthTokenByRefreshServiceAsync generateAuthTokenByRefreshServiceAsync,
            LoginInWithTokenServiceAsync loginInWithTokenServiceAsync,
            ValidateRefreshTokenService validateRefreshTokenService,
            RefreshTokenSavingConnector refreshTokenSavingConnector,
            RefreshTokenDtoMapper refreshTokenDtoMapper,
            AuthTokenDtoMapper authTokenDtoMapper,
            RowLoginInDetailsDtoMapper rowLoginInDetailsDtoMapper
    ) {
        return new BaseAuthTokenGeneratorAsync(
                generateAuthTokenByRefreshServiceAsync,
                loginInWithTokenServiceAsync,
                validateRefreshTokenService,
                refreshTokenSavingConnector,
                refreshTokenDtoMapper,
                authTokenDtoMapper,
                rowLoginInDetailsDtoMapper
        );
    }
}