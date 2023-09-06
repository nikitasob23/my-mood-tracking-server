package com.niksob.gateway.controller.auth.token.update;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.refresh.RefreshTokenDtoMapper;
import com.niksob.gateway.rest.path.controller.update.UpdateAuthTokenControllerPaths;
import com.niksob.gateway.service.auth.token.update.UpdateAuthTokenService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(UpdateAuthTokenControllerPaths.BASE_URI)
@AllArgsConstructor
public class UpdateAuthTokenController {

    @NonNull
    private final UpdateAuthTokenService updateAuthTokenService;

    @NonNull
    private final RefreshTokenDtoMapper refreshTokenDtoMapper;

    @NonNull
    private final AuthTokenDtoMapper authTokenDtoMapper;

    @PostMapping
    public Mono<AuthTokenDto> update(@RequestBody RefreshTokenDto refreshTokenDto) {
            return Mono.just(refreshTokenDto)
                    .map(refreshTokenDtoMapper::fromDto)
                    .flatMap(updateAuthTokenService::execute)
                    .map(authTokenDtoMapper::toDto);
    }
}
