package com.niksob.authorization.values.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JwtKey {
    SUBJECT_KEY("sub"),
    EXPIRATION_KEY("exp");

    private final String value;
}
