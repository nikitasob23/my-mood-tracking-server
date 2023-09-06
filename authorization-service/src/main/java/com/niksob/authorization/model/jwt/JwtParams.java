package com.niksob.authorization.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtParams {
    private String subject;
    private JwtClaims claims;
    private int expirationInMinutes;
}
