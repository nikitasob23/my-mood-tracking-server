package com.niksob.authorization_model.dto.signup;

import java.util.Set;

public record SignupDetailsDto(
        String username,
        String nickname,
        String rowPassword,
        Set<String> roles
) {
}
