package com.niksob.gateway.config.web.security;

import com.niksob.gateway.rest.path.controller.generating.GeneratingAuthTokenControllerPaths;
import com.niksob.gateway.rest.path.controller.signup.SignupControllerPaths;
import com.niksob.gateway.rest.path.controller.update.UpdateAuthTokenControllerPaths;
import com.niksob.gateway.security.web.filter.token.access.AccessTokenFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    private final AccessTokenFilter accessTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                SignupControllerPaths.BASE_URI,
                                UpdateAuthTokenControllerPaths.BASE_URI,
                                GeneratingAuthTokenControllerPaths.BASE_URI
                        )
                        .permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .addFilterAfter(accessTokenFilter, UsernamePasswordAuthenticationFilter.class)
                )
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    public AuthenticationManager configure(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authManagerBuilder.userDetailsService(userDetailsService);
        return authManagerBuilder.build();
    }
}
