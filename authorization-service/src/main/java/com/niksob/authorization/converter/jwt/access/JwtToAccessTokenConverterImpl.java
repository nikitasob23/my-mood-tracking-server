package com.niksob.authorization.converter.jwt.access;

import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization_model.model.token.access.AccessToken;
import org.springframework.stereotype.Component;

@Component
public class JwtToAccessTokenConverterImpl implements JwtToAccessTokenConverter {
    @Override
    public AccessToken convert(Jwt jwt) {
        return new AccessToken(jwt.value());
    }
}
