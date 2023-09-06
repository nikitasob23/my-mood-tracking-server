package com.niksob.authorization.converter.token.auth_token;

import com.niksob.authorization.converter.Converter;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.authorization_model.dto.token.AuthTokenDto;

public interface AuthTokenResponseConverter extends Converter<AuthToken, AuthTokenDto> {
}
