package com.niksob.authorization.service.db.token.refresh;

import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.authorization.service.ExecutableService;
import reactor.core.publisher.Mono;

public interface RefreshTokenSavingConnector {
    Mono<Void> save(RefreshToken refreshToken);
}
