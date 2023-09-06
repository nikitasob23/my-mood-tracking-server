package com.niksob.gateway.config.rest.path.controller.generating;

import com.niksob.gateway.rest.path.controller.generating.GeneratingAuthTokenControllerPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratingAuthTokenControllerPathsConfig {
    @Bean("generating_auth_token_by_user_details")
    public String getGeneratingAuthTokenControllerPath() {
        return GeneratingAuthTokenControllerPaths.BASE_URI;
    }
}
