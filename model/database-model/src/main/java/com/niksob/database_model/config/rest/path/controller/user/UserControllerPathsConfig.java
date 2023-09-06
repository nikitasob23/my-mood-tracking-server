package com.niksob.database_model.config.rest.path.controller.user;

import com.niksob.database_model.rest.path.controller.user.UserControllerPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserControllerPathsConfig {

    @Bean("loading_user_by_username_uri")
    public String getLoadingUserByUsernameUri() {
        return UserControllerPaths.BASE_URI + UserControllerPaths.LOADING;
    }

    @Bean("saving_user_uri")
    public String getSavingUserUri() {
        return UserControllerPaths.BASE_URI + UserControllerPaths.SAVING;
    }

    @Bean("loading_refresh_token_uri")
    public String getLoadingRefreshTokenUri() {
        return UserControllerPaths.BASE_URI + UserControllerPaths.REFRESH_TOKEN + UserControllerPaths.LOADING;
    }

    @Bean("saving_refresh_token_uri")
    public String getSavingRefreshTokenUri() {
        return UserControllerPaths.BASE_URI + UserControllerPaths.REFRESH_TOKEN + UserControllerPaths.SAVING;
    }
}