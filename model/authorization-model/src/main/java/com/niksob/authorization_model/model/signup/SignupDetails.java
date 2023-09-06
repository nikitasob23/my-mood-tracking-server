package com.niksob.authorization_model.model.signup;

import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;

import java.util.Set;

public record SignupDetails(
        Username username,
        String nickname,
        EncodedPassword encodedPassword,
        Set<Role> roles
) {
}
