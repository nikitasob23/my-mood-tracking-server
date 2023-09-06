package com.niksob.authorization_model.model.signup;

import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;

import java.util.Set;

public record RowSignupDetails(
        Username username,
        String nickname,
        RowPassword rowPassword,
        Set<Role> roles
) {
}
