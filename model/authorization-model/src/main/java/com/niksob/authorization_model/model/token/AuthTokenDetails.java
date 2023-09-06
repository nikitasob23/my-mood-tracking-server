package com.niksob.authorization_model.model.token;

import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;

import java.util.Set;

public record AuthTokenDetails(
        Username username,
        RowPassword rowPassword,
        Set<Role> roles
) {
}
