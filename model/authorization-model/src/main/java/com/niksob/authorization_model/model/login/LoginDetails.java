package com.niksob.authorization_model.model.login;

import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.username.Username;

public record LoginDetails(
        Username username,
        EncodedPassword encodedPassword
) {
}
