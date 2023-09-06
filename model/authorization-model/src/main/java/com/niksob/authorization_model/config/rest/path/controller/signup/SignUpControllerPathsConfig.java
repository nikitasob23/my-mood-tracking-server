package com.niksob.authorization_model.config.rest.path.controller.signup;

import com.niksob.authorization_model.rest.path.controller.signup.SignupControllerPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpControllerPathsConfig {
    @Bean("signup_uri")
    public String getSignupUri() {
        return SignupControllerPaths.BASE_URI;
    }
}
