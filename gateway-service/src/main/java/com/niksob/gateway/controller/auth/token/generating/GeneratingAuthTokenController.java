package com.niksob.gateway.controller.auth.token.generating;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.mapper.auth.login.RowLoginInDetailsDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.gateway.rest.path.controller.generating.GeneratingAuthTokenControllerPaths;
import com.niksob.gateway.service.auth.token.generation.GenerateAuthTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(GeneratingAuthTokenControllerPaths.BASE_URI)
@AllArgsConstructor
public class GeneratingAuthTokenController {

    private final GenerateAuthTokenService generateAuthTokenService;

    private final RowLoginInDetailsDtoMapper rowLoginInDetailsDtoMapper;

    private final AuthTokenDtoMapper authTokenDtoMapper;

    @PostMapping
    public Mono<AuthTokenDto> generate(@RequestBody RowLoginInDetailsDto rowLoginInDetailsDto) {

        return Mono.just(rowLoginInDetailsDto)
                .map(rowLoginInDetailsDtoMapper::fromDto)
                .flatMap(generateAuthTokenService::execute)
                .map(authTokenDtoMapper::toDto);
    }
}
