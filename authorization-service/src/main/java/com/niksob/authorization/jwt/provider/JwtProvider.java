package com.niksob.authorization.jwt.provider;

import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtParams;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public interface JwtProvider {

    Jwt generate(JwtParams jwtParams);

    boolean validate(Jwt jwt);

    Claims getClaims(@NotNull Jwt jwt);

    JwtParams getJwtParams(@NonNull Jwt jwt);
}
