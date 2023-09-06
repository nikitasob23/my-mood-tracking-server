package com.niksob.authorization.value.user.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserValues {

    USERNAME("u@gmail.com"),
    NICKNAME("u"),
    ROW_PASSWORD("TEST_ROW_PASSWORD"),

    ENCODED_PASSWORD("TEST_ENCODED_PASSWORD"),
    DEF_ROLE("USER"),
    ROLES_CLAIM_KEY("ROLES_KEY");

    public final String value;
}
