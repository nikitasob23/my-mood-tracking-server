package com.niksob.gateway.service.auth.login.jwt;

import com.niksob.gateway.service.ExecutableService;
import com.niksob.authorization_model.model.token.access.AccessToken;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginInByAccessTokenService extends ExecutableService<AccessToken, UserDetails> {
}
