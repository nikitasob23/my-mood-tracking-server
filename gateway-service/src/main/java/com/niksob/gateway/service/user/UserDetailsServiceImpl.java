package com.niksob.gateway.service.user;

import com.niksob.gateway.client.HttpClient;
import com.niksob.gateway.mapper.user.security.SecurityUserDetailsDtoMapper;
import com.niksob.gateway.dto.user.security.SecurityUserDetailsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final HttpClient httpClient;

    private final SecurityUserDetailsDtoMapper securityUserDetailsDtoMapper;

    @Value("client.uri.user.loading")
    private String loadUserUri;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final SecurityUserDetailsDto securityUserDetailsDto = httpClient.sendPostRequest(
                loadUserUri,
                username, String.class,
                SecurityUserDetailsDto.class
        ).block();

        return securityUserDetailsDtoMapper.fromDto(securityUserDetailsDto);
    }
}
