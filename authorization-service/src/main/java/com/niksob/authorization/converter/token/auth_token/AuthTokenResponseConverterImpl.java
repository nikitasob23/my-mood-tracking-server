package com.niksob.authorization.converter.token.auth_token;

import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenResponseConverterImpl implements AuthTokenResponseConverter {
    @Override
    public AuthTokenDto convert(AuthToken authToken) {
        return new AuthTokenDto(
                authToken.getAccess().getValue(),
                authToken.getRefresh().getValue()
        );
    }
}
