package com.niksob.authorization.service.auth.token.generation;

import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.authorization.service.ExecutableService;
import com.niksob.domain_model.model.user.UserInfo;

public interface GenerateAuthTokenService extends ExecutableService<UserInfo, AuthToken> {
}
