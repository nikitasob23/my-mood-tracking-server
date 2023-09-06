package com.niksob.database_controller.service.user.loading;

import com.niksob.database_controller.service.ExecutableService;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.username.Username;
import reactor.core.publisher.Mono;

public interface LoadUserByUsernameService extends ExecutableService<Username, Mono<UserInfo>> {
}
