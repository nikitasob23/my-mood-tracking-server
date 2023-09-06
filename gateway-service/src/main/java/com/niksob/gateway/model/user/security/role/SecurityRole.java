package com.niksob.gateway.model.user.security.role;

import org.springframework.security.core.GrantedAuthority;

public enum SecurityRole implements GrantedAuthority {

    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
