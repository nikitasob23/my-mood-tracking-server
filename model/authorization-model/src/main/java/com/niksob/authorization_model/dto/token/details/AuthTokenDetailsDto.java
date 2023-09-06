package com.niksob.authorization_model.dto.token.details;

import java.util.Set;

public record AuthTokenDetailsDto(
        String username,
        String rowPassword,
        Set<String> roles
) {
}
