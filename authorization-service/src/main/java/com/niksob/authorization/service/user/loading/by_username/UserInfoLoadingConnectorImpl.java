package com.niksob.authorization.service.user.loading.by_username;

import com.niksob.authorization.http.client.HttpClient;
import com.niksob.authorization.config.exception.ExceptionMessagesConfig;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.mapper.user.UserInfoDtoMapper;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.username.Username;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.util.UriComponentsBuilder;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Component
public class UserInfoLoadingConnectorImpl implements UserInfoLoadingConnector {
    private final HttpClient httpClient;
    private final UserInfoDtoMapper userInfoDtoMapper;
    private final String loadingUserByUsernameUri;
    private final UsernameNotFoundException usernameNotFoundException;

    public UserInfoLoadingConnectorImpl(
            ExceptionMessagesConfig exceptionMessagesConfig,
            HttpClient httpClient,
            UserInfoDtoMapper userInfoDtoMapper,
            @Qualifier("loading_user_by_username_uri") String loadingUserByUsernameUri
    ) {
        this.usernameNotFoundException = Stream.of(exceptionMessagesConfig)
                .map(ExceptionMessagesConfig::getUsernameNotFound) // get exception message from config
                .map(UsernameNotFoundException::new)
                .findFirst().get();
        this.httpClient = httpClient;
        this.userInfoDtoMapper = userInfoDtoMapper;
        this.loadingUserByUsernameUri = loadingUserByUsernameUri;
    }

    @Override
    public Mono<UserInfo> loadByUsernameAsync(@NonNull Username username) {
        return createUriWithParamsAsync(username)
                .flatMap(uriWithParams -> httpClient.sendGetRequest(uriWithParams, UserInfoDto.class))
                .switchIfEmpty(Mono.error(usernameNotFoundException))
                .map(userInfoDtoMapper::fromDto);
    }

    private Mono<String> createUriWithParamsAsync(Username username) {
        return Mono.just(username)
                .map(this::createUsernameParamName)
                .map(usernameParamName -> createUriWithParams(usernameParamName, username));
    }

    private String createUriWithParams(@NonNull String usernameParamName, @NonNull Username username) {
        return UriComponentsBuilder.fromUriString(loadingUserByUsernameUri)
                .queryParam(usernameParamName, username.value())
                .toUriString();
    }

    private String createUsernameParamName(@NonNull Username username) {
        return username.getClass()
                .getSimpleName()
                .toLowerCase();
    }
}
