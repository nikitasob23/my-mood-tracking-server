package com.niksob.authorization.converter.jwt.refresh;

import com.niksob.authorization.converter.Converter;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.domain_model.model.token.refresh.RefreshToken;

public interface JwtToRefreshTokenConverter extends Converter<Jwt, RefreshToken> {
}
