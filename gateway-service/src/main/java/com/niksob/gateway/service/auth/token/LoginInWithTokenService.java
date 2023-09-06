package com.niksob.gateway.service.auth.token;

import com.niksob.gateway.service.ExecutableService;
import com.niksob.authorization_model.model.login.UserLoginInDetails;
import com.niksob.authorization_model.model.token.AuthToken;
import reactor.core.publisher.Mono;

public interface LoginInWithTokenService extends ExecutableService<UserLoginInDetails, Mono<AuthToken>> {
}
