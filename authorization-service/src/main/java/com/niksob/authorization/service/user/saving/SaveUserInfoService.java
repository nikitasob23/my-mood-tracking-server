package com.niksob.authorization.service.user.saving;

import com.niksob.authorization.service.ExecutableService;
import com.niksob.domain_model.model.user.UserInfo;
import reactor.core.publisher.Mono;

public interface SaveUserInfoService extends ExecutableService<UserInfo, Mono<Void>> {
}
