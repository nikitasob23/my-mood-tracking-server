package com.niksob.authorization.service.auth.token.generation.by_refresh;

import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.authorization.service.ExecutableService;
import reactor.core.publisher.Mono;

public interface GenerateAuthTokenByRefreshServiceAsync extends ExecutableService<RefreshToken, Mono<AuthToken>> {
}
