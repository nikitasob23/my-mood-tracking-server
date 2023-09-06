package com.niksob.authorization_model.model.login;

import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.username.Username;

public record UserLoginInDetails(
        Username username,
        RowPassword rowPassword
) {
}
