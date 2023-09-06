package com.niksob.gateway.service.auth.token.update;

import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.gateway.service.ExecutableService;
import reactor.core.publisher.Mono;

public interface UpdateAuthTokenService extends ExecutableService<RefreshToken, Mono<AuthToken>> {
}
