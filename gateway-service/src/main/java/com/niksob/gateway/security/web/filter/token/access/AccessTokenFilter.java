package com.niksob.gateway.security.web.filter.token.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niksob.gateway.exception.auth.token.AuthTokenException;
import com.niksob.gateway.security.web.auth.JwtAuthentication;
import com.niksob.gateway.service.auth.login.jwt.LoginInByAccessTokenService;
import com.niksob.gateway.service.token.request.GetRequestJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class AccessTokenFilter extends GenericFilterBean {

    private final GetRequestJwtService getRequestJwtService;

    private final LoginInByAccessTokenService loginInByAccessTokenService;

    private final ObjectMapper mapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException {

        try {
            Stream.of(request)
                    .map(getRequestJwtService::execute)
                    .filter(Objects::nonNull) // If request not contains bearer token, skip next steps
                    .map(loginInByAccessTokenService::execute)
                    .filter(Objects::nonNull) // If user exists then it's registered
                    .filter(UserDetails::isAccountNonLocked)
                    .forEach(this::authenticate);
        }  catch (AuthTokenException e) {
            sendTokenExceptionResponse((HttpServletResponse) response, "Invalid token");
        }
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            sendTokenExceptionResponse((HttpServletResponse) response, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
    }

    private void sendTokenExceptionResponse(HttpServletResponse response, String exceptionMessageStr) throws IOException {
        final Map<String, String> exceptionMessage = Map.of("message", exceptionMessageStr);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), exceptionMessage);
    }

    private void authenticate(UserDetails userDetails) {
        Stream.of(userDetails)
                .map(JwtAuthentication::new)
                .peek(SecurityContextHolder.getContext()::setAuthentication) // Set authentication to context
                .forEach(JwtAuthentication::authenticate);
    }
}
