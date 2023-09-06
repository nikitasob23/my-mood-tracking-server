package com.niksob.authorization_model.mapper.auth.token;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.model.token.AuthToken;
import com.niksob.authorization_model.model.token.access.AccessToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:49+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class AuthTokenDtoMapperImpl implements AuthTokenDtoMapper {

    @Override
    public AuthTokenDto toDto(AuthToken authToken) {
        if ( authToken == null ) {
            return null;
        }

        AuthTokenDto authTokenDto = new AuthTokenDto();

        authTokenDto.setAccess( authTokenAccessValue( authToken ) );
        authTokenDto.setRefresh( authTokenRefreshValue( authToken ) );

        return authTokenDto;
    }

    @Override
    public AuthToken fromDto(AuthTokenDto authTokenDto) {
        if ( authTokenDto == null ) {
            return null;
        }

        AuthToken authToken = new AuthToken();

        authToken.setAccess( authTokenDtoToAccessToken( authTokenDto ) );
        authToken.setRefresh( authTokenDtoToRefreshToken( authTokenDto ) );

        return authToken;
    }

    private String authTokenAccessValue(AuthToken authToken) {
        if ( authToken == null ) {
            return null;
        }
        AccessToken access = authToken.getAccess();
        if ( access == null ) {
            return null;
        }
        String value = access.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String authTokenRefreshValue(AuthToken authToken) {
        if ( authToken == null ) {
            return null;
        }
        RefreshToken refresh = authToken.getRefresh();
        if ( refresh == null ) {
            return null;
        }
        String value = refresh.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    protected AccessToken authTokenDtoToAccessToken(AuthTokenDto authTokenDto) {
        if ( authTokenDto == null ) {
            return null;
        }

        AccessToken accessToken = new AccessToken();

        accessToken.setValue( authTokenDto.getAccess() );

        return accessToken;
    }

    protected RefreshToken authTokenDtoToRefreshToken(AuthTokenDto authTokenDto) {
        if ( authTokenDto == null ) {
            return null;
        }

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setValue( authTokenDto.getRefresh() );

        return refreshToken;
    }
}
