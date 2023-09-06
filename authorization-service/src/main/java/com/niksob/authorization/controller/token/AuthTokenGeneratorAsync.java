package com.niksob.authorization.controller.token;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import reactor.core.publisher.Mono;

public interface AuthTokenGeneratorAsync {
    Mono<AuthTokenDto> generateByLoginInDetails(RowLoginInDetailsDto rowLoginInDetailsDto);

    Mono<AuthTokenDto> generateByRefreshToken(RefreshTokenDto refreshTokenDto);
}
