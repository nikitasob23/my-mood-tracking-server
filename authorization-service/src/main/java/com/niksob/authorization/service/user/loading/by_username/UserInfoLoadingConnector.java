package com.niksob.authorization.service.user.loading.by_username;

import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.username.Username;
import lombok.NonNull;
import reactor.core.publisher.Mono;

public interface UserInfoLoadingConnector {

    Mono<UserInfo> loadByUsernameAsync(@NonNull Username username);
}
