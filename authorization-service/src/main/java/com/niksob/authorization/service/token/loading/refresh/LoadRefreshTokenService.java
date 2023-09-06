package com.niksob.authorization.service.token.loading.refresh;

import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.authorization.service.ExecutableService;
import com.niksob.domain_model.model.user.login.username.Username;
import reactor.core.publisher.Mono;

public interface LoadRefreshTokenService extends ExecutableService<Username, Mono<RefreshToken>> {
}
