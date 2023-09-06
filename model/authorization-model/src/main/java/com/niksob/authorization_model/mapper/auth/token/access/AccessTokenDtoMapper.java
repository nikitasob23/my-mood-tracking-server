package com.niksob.authorization_model.mapper.auth.token.access;

import com.niksob.authorization_model.dto.token.access.AccessTokenDto;
import com.niksob.authorization_model.model.token.access.AccessToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessTokenDtoMapper {

    AccessTokenDto toDto(AccessToken accessToken);
}
