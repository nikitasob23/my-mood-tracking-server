package com.niksob.gateway.service.auth.token.generation;

import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.gateway.service.ExecutableService;
import reactor.core.publisher.Mono;

public interface GenerateAuthTokenService extends ExecutableService<RowLoginInDetails, Mono<AuthToken>> {
}
