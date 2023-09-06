package com.niksob.gateway.service.token.request;

import com.niksob.gateway.service.ExecutableService;
import com.niksob.authorization_model.model.token.access.AccessToken;
import jakarta.servlet.ServletRequest;

public interface GetRequestJwtService extends ExecutableService<ServletRequest, AccessToken> {
}
