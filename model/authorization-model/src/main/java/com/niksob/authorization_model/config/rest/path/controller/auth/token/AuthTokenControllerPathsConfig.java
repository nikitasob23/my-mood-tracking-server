package com.niksob.authorization_model.config.rest.path.controller.auth.token;

import com.niksob.authorization_model.rest.path.controller.token.AuthTokenControllerPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthTokenControllerPathsConfig {

    @Bean("generating_auth_token_by_login_details_uri")
    public String getGeneratingAuthTokenByLoginDetailsUri() {
        return AuthTokenControllerPaths.BASE_URI + AuthTokenControllerPaths.GENERATION;
    }

    @Bean("generating_auth_token_by_refresh_uri")
    public String getGeneratingAuthTokenByRefreshUri() {
        return AuthTokenControllerPaths.BASE_URI + AuthTokenControllerPaths.GENERATION_REFRESH;
    }
}
