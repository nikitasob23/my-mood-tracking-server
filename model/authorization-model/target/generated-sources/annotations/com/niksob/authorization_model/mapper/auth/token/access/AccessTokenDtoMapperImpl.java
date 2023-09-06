package com.niksob.authorization_model.mapper.auth.token.access;

import com.niksob.authorization_model.dto.token.access.AccessTokenDto;
import com.niksob.authorization_model.model.token.access.AccessToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:49+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class AccessTokenDtoMapperImpl implements AccessTokenDtoMapper {

    @Override
    public AccessTokenDto toDto(AccessToken accessToken) {
        if ( accessToken == null ) {
            return null;
        }

        String value = null;

        value = accessToken.getValue();

        AccessTokenDto accessTokenDto = new AccessTokenDto( value );

        return accessTokenDto;
    }
}
