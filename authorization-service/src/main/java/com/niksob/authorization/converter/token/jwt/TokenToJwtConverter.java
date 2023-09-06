package com.niksob.authorization.converter.token.jwt;

import com.niksob.authorization.converter.Converter;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.domain_model.model.token.Token;

public interface TokenToJwtConverter extends Converter<Token, Jwt> {
}
