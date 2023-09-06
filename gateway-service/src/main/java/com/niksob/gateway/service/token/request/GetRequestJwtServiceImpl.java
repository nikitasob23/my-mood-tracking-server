package com.niksob.gateway.service.token.request;

import com.niksob.authorization_model.model.token.access.AccessToken;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

@Service
public class GetRequestJwtServiceImpl implements GetRequestJwtService {
    private static final String AUTHORIZATION = "Authorization";

    @Override
    public AccessToken execute(@NonNull ServletRequest request) {
        return Stream.of(request)
                .map(this::convertToHttpServletRequest)
                .map(this::getAuthHeader) // Get bearer
                .filter(StringUtils::hasText)
                .filter(this::isBearerRequest)
                .map(this::getToken)
                .map(AccessToken::new)
                .findFirst().orElse(null);
    }

    private HttpServletRequest convertToHttpServletRequest(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private String getAuthHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

    private boolean isBearerRequest(String bearer) {
        return bearer.startsWith("Bearer ");
    }

    private String getToken(String bearer) {
        return bearer.substring(7);
    }
}
