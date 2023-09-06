package com.niksob.gateway.service.auth.token.generation;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.mapper.auth.login.RowLoginInDetailsDtoMapper;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.gateway.client.HttpClient;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.authorization_model.model.token.AuthToken;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GenerateAuthTokenServiceImpl implements GenerateAuthTokenService {

    private final HttpClient httpClient;

    private final RowLoginInDetailsDtoMapper rowLoginInDetailsDtoMapper;

    private final AuthTokenDtoMapper authTokenDtoMapper;

    private final String generatingAuthTokenUri;

    public GenerateAuthTokenServiceImpl(
            @NonNull HttpClient httpClient,
            @NonNull RowLoginInDetailsDtoMapper rowLoginInDetailsDtoMapper,
            @NonNull AuthTokenDtoMapper authTokenDtoMapper,
            @NonNull @Qualifier("generating_auth_token_by_login_details_uri") String generatingAuthTokenUri
    ) {
        this.httpClient = httpClient;
        this.rowLoginInDetailsDtoMapper = rowLoginInDetailsDtoMapper;
        this.authTokenDtoMapper = authTokenDtoMapper;
        this.generatingAuthTokenUri = generatingAuthTokenUri;
    }

    @Override
    public Mono<AuthToken> execute(RowLoginInDetails rowLoginInDetails) {

        final RowLoginInDetailsDto rowLoginInDetailsDto = rowLoginInDetailsDtoMapper.toDto(rowLoginInDetails);

        try {

            return httpClient.sendPostRequest(
                            generatingAuthTokenUri,
                            rowLoginInDetailsDto, RowLoginInDetailsDto.class,
                            AuthTokenDto.class
                    )
                    .map(authTokenDtoMapper::fromDto);
        } catch (Exception e) {
            return null;
        }
    }
}
