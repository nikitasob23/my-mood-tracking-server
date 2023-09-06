package com.niksob.authorization.converter.jwt.refresh;

import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class JwtToRefreshTokenConverterImpl implements JwtToRefreshTokenConverter {
    @Override
    public RefreshToken convert(Jwt jwt) {
        return new RefreshToken(jwt.value());
    }
}
