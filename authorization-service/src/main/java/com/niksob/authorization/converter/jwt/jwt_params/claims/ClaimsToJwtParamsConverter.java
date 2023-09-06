package com.niksob.authorization.converter.jwt.jwt_params.claims;

import com.niksob.authorization.converter.Converter;
import com.niksob.authorization.model.jwt.JwtParams;
import io.jsonwebtoken.Claims;

public interface ClaimsToJwtParamsConverter extends Converter<Claims, JwtParams> {
}
