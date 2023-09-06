package com.niksob.authorization.value.user.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum JwtValues {

    ACCESS_TOKEN_KEY("ACCESS_TOKEN_KEY"),
    REFRESH_TOKEN_KEY("REFRESH_TOKEN_KEY");

    public final String value;
}
