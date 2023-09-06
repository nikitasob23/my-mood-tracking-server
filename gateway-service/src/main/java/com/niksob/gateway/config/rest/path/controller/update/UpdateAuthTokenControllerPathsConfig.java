package com.niksob.gateway.config.rest.path.controller.update;

import com.niksob.gateway.rest.path.controller.update.UpdateAuthTokenControllerPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateAuthTokenControllerPathsConfig {
    @Bean("update_auth_token_controller_uri")
    public String getUpdateAuthTokenControllerPaths() {
        return UpdateAuthTokenControllerPaths.BASE_URI;
    }
}
