package com.niksob.gateway.config.model.dto;

import com.niksob.domain_model.model.user.login.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestUserValues {
    EMAIL("123@gmail.com"),
    NICKNAME("u"),
    ROW_PASSWORD("row_p"),
    ENCODED_PASSWORD("encoded_p"),
    USER_ROLE(Role.USER.name()),
    ACCESS_TOKEN("1234567890"),
    REFRESH_TOKEN("0987654321");

    private final String value;
}
