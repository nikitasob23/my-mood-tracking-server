package com.niksob.authorization.service.user.saving;

import com.niksob.authorization.config.exception.ExceptionMessagesConfig;
import com.niksob.authorization.http.client.HttpClient;
import com.niksob.authorization_model.exception.db.refresh_token.save.DatabaseException;
import com.niksob.authorization_model.exception.db.user.UserExistsException;
import com.niksob.authorization.service.user.loading.by_username.UserInfoLoadingConnector;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.mapper.user.UserInfoDtoMapper;
import com.niksob.domain_model.model.user.UserInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

@Service
public class SaveUserInfoServiceImpl implements SaveUserInfoService {
    private final HttpClient httpClient;
    private final UserInfoLoadingConnector userInfoLoadingConnector;
    private final UserInfoDtoMapper userInfoDtoMapper;
    private final String savingUserUri;
    private final UserExistsException userExistsException;

    public SaveUserInfoServiceImpl(
            @NonNull ExceptionMessagesConfig exceptionMessagesConfig,
            @NonNull UserInfoLoadingConnector userInfoLoadingConnector,
            @NonNull UserInfoDtoMapper userInfoDtoMapper,
            @NonNull HttpClient httpClient,
            @NonNull @Qualifier("saving_user_uri") String savingUserUri
    ) {
        this.userExistsException = Stream.of(exceptionMessagesConfig)
                .map(ExceptionMessagesConfig::getUserAlreadyExists)
                .map(UserExistsException::new)
                .findFirst().get();
        this.userInfoLoadingConnector = userInfoLoadingConnector;
        this.userInfoDtoMapper = userInfoDtoMapper;
        this.httpClient = httpClient;
        this.savingUserUri = savingUserUri;
    }

    @Override
    public Mono<Void> execute(UserInfo userInfo) {

        return isUserExistsAsync(userInfo)
                .filter(isUserExists -> isUserExists).switchIfEmpty(Mono.error(userExistsException))
                .then(httpClient.sendPostRequest(
                        savingUserUri,
                        userInfoDtoMapper.toDto(userInfo), UserInfoDto.class,
                        Void.class
                ));
    }

    private Mono<Boolean> isUserExistsAsync(UserInfo userInfo) {
        return Mono.just(userInfo.getLoginInfo()
                        .getUsername())
                .flatMap(userInfoLoadingConnector::loadByUsernameAsync)
                .map(u -> true)
                .onErrorReturn(false);
    }
}
