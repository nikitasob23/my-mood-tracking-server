package com.niksob.authorization.converter.token.refresh;

import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenDtoConverterImpl implements RefreshTokenDtoConverter {
    @Override
    public RefreshToken convert(RefreshTokenDto refreshTokenDto) {
        return new RefreshToken(refreshTokenDto.getValue());
    }
}
