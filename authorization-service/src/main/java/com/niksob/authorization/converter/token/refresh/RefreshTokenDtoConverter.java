package com.niksob.authorization.converter.token.refresh;

import com.niksob.authorization.converter.Converter;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;

public interface RefreshTokenDtoConverter extends Converter<RefreshTokenDto, RefreshToken> {
}
