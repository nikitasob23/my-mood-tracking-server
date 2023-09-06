package com.niksob.authorization_model.model.login;

import com.niksob.domain_model.model.user.login.username.Username;
import com.niksob.domain_model.model.user.login.password.RowPassword;

public record RowLoginInDetails(
    Username username,
    RowPassword rowPassword
) {
}
