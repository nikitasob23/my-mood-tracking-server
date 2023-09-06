package com.niksob.authorization_model.mapper.auth.token.refresh;

import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:49+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class RefreshTokenDtoMapperImpl implements RefreshTokenDtoMapper {

    @Override
    public RefreshToken fromDto(RefreshTokenDto dto) {
        if ( dto == null ) {
            return null;
        }

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setValue( dto.getValue() );

        return refreshToken;
    }

    @Override
    public RefreshTokenDto toDto(RefreshToken refreshToken) {
        if ( refreshToken == null ) {
            return null;
        }

        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();

        refreshTokenDto.setValue( refreshToken.getValue() );

        return refreshTokenDto;
    }
}
