package com.niksob.authorization.service.auth.token.generation.by_refresh;

import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.authorization.service.auth.token.generation.GenerateAuthTokenService;
import com.niksob.authorization.converter.token.username.TokenToUsernameConverter;
import com.niksob.authorization.service.user.loading.by_username.UserInfoLoadingConnector;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GenerateAuthTokenByRefreshServiceAsyncImpl implements GenerateAuthTokenByRefreshServiceAsync {

    @NonNull
    private final UserInfoLoadingConnector userInfoLoadingConnector;

    @NonNull
    private final TokenToUsernameConverter tokenToUsernameConverter;

    @NonNull
    private final GenerateAuthTokenService generateAuthTokenService;

    public GenerateAuthTokenByRefreshServiceAsyncImpl(
            @NonNull UserInfoLoadingConnector userInfoLoadingConnector,
            @NonNull @Qualifier("get_username_from_refresh_token_service")
            TokenToUsernameConverter tokenToUsernameConverter,
            @NonNull GenerateAuthTokenService generateAuthTokenService
    ) {
        this.userInfoLoadingConnector = userInfoLoadingConnector;
        this.tokenToUsernameConverter = tokenToUsernameConverter;
        this.generateAuthTokenService = generateAuthTokenService;
    }

    @Override
    public Mono<AuthToken> execute(RefreshToken refreshToken) {
        return Mono.just(refreshToken)
                .map(tokenToUsernameConverter::execute)
                .flatMap(userInfoLoadingConnector::loadByUsernameAsync)
                .map(generateAuthTokenService::execute);
    }
}
