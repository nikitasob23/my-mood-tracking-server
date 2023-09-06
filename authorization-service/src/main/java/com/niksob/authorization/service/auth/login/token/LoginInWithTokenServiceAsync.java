package com.niksob.authorization.service.auth.login.token;

import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.authorization.service.ExecutableService;
import reactor.core.publisher.Mono;

public interface LoginInWithTokenServiceAsync extends ExecutableService<RowLoginInDetails, Mono<AuthToken>> {
}
