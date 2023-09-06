package com.niksob.authorization.converter.token.username;

import com.niksob.authorization.service.ExecutableService;
import com.niksob.domain_model.model.token.Token;
import com.niksob.domain_model.model.user.login.username.Username;

public interface TokenToUsernameConverter extends ExecutableService<Token, Username> {
}
