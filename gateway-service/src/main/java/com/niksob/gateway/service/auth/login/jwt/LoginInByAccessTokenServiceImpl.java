package com.niksob.gateway.service.auth.login.jwt;

import com.niksob.authorization_model.dto.token.access.AccessTokenDto;
import com.niksob.gateway.client.HttpClient;
import com.niksob.authorization_model.mapper.auth.token.access.AccessTokenDtoMapper;
import com.niksob.gateway.mapper.user.security.SecurityUserDetailsDtoMapper;
import com.niksob.gateway.dto.user.security.SecurityUserDetailsDto;
import com.niksob.authorization_model.model.token.access.AccessToken;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginInByAccessTokenServiceImpl implements LoginInByAccessTokenService {

    private final HttpClient httpClient;

    private final AccessTokenDtoMapper accessTokenDtoMapper;

    private final SecurityUserDetailsDtoMapper securityUserDetailsDtoMapper;

    @Value("client.uri.auth.login.jwt")
    private String jwtLoginUri;

    @Override
    public UserDetails execute(AccessToken accessToken) {

        final AccessTokenDto accessTokenDto = accessTokenDtoMapper.toDto(accessToken);

        final SecurityUserDetailsDto securityUserDetailsDto = httpClient.sendPostRequest(
                jwtLoginUri,
                accessTokenDto, AccessTokenDto.class,
                SecurityUserDetailsDto.class
        ).block();

        return securityUserDetailsDtoMapper.fromDto(securityUserDetailsDto);
    }
}
