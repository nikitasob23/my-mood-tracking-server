package com.niksob.authorization_model.mapper.auth.token;

import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.model.token.AuthToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthTokenDtoMapper {
    @Mapping(source = "access.value", target = "access")
    @Mapping(source = "refresh.value", target = "refresh")
    AuthTokenDto toDto(AuthToken authToken);

    @Mapping(source = "access", target = "access.value")
    @Mapping(source = "refresh", target = "refresh.value")
    AuthToken fromDto(AuthTokenDto authTokenDto);
}
