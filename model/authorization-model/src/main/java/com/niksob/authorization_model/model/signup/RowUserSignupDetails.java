package com.niksob.authorization_model.model.signup;

import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.username.Username;

public record RowUserSignupDetails(
        Username username,
        String nickname,
        RowPassword rowPassword
) {
}
