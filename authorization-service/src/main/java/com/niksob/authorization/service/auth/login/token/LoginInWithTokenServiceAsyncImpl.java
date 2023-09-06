package com.niksob.authorization.service.auth.login.token;

import com.niksob.authorization_model.exception.auth.WrongPasswordException;
import com.niksob.authorization.model.login.password.ComparedPasswords;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.authorization.service.auth.login.ComparePasswordsService;
import com.niksob.authorization.service.auth.token.generation.GenerateAuthTokenService;
import com.niksob.authorization.service.user.loading.by_username.UserInfoLoadingConnector;
import com.niksob.domain_model.model.user.UserInfo;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginInWithTokenServiceAsyncImpl implements LoginInWithTokenServiceAsync {
    private final UserInfoLoadingConnector userInfoLoadingConnector;
    private final ComparePasswordsService comparePasswordsService;
    private final GenerateAuthTokenService generateAuthTokenService;

    @Override
    public Mono<AuthToken> execute(@NonNull RowLoginInDetails rowLoginInDetails) {

        return userInfoLoadingConnector.loadByUsernameAsync(rowLoginInDetails.username())
                .doOnNext(userInfo -> comparePasswords(rowLoginInDetails, userInfo))
                .map(generateAuthTokenService::execute);
    }

    private void comparePasswords(RowLoginInDetails rowLoginInDetails, UserInfo userInfo) {
        final ComparedPasswords comparedPasswords = new ComparedPasswords(
                rowLoginInDetails.rowPassword(),
                userInfo.getLoginInfo().getEncodedPassword()
        );
        if (!comparePasswordsService.execute(comparedPasswords)) {
            throw new WrongPasswordException();
        }
    }
}
