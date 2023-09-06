package com.niksob.authorization.converter.token.username;

import com.niksob.authorization.converter.token.jwt.TokenToJwtConverter;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.domain_model.model.token.Token;
import com.niksob.domain_model.model.user.login.username.Username;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.stream.Stream;

@AllArgsConstructor
public class TokenToUsernameConverterImpl implements TokenToUsernameConverter {

    @NonNull
    private final JwtProvider jwtProvider;

    @NonNull
    private final TokenToJwtConverter tokenToJwtConverter;

    @Override
    public Username execute(Token token) {
        return Stream.of(token)
                .map(tokenToJwtConverter::convert)
                .map(jwtProvider::getJwtParams)
                .map(JwtParams::getSubject)
                .map(Username::new)
                .findFirst().get();
    }
}
