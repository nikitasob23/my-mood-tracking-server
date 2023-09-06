package com.niksob.authorization.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class JwtClaims {
    private Map<String, Object> storage = Map.of();
}
