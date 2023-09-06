package com.niksob.authorization.converter.token.jwt;

import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.domain_model.model.token.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenToJwtConverterImpl implements TokenToJwtConverter {
    @Override
    public Jwt convert(Token token) {
        return new Jwt(token.getValue());
    }
}
