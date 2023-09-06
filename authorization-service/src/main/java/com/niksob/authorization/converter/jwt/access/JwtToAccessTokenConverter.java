package com.niksob.authorization.converter.jwt.access;

import com.niksob.authorization.converter.Converter;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization_model.model.token.access.AccessToken;

public interface JwtToAccessTokenConverter extends Converter<Jwt, AccessToken> {
}
