package com.niksob.authorization.service.token.validation;

import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.authorization.service.ExecutableService;

public interface ValidateRefreshTokenService extends ExecutableService<RefreshToken, Boolean> {
}
