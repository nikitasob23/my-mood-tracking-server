package com.niksob.gateway.config.rest.path.controller.signup;

import com.niksob.gateway.rest.path.controller.signup.SignupControllerPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignupControllerPathsConfig {
    @Bean("gateway_signup_uri")
    public String getGatewaySignupUri() {
        return SignupControllerPaths.BASE_URI;
    }
}
