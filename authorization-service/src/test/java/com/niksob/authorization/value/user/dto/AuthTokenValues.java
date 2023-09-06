package com.niksob.authorization.value.user.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthTokenValues {

    ACCESS_TOKEN("TEST_ACCESS_TOKEN"),
    REFRESH_TOKEN("TEST_REFRESH_TOKEN");

    public final String value;
}
