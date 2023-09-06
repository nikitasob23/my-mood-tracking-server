package com.niksob.authorization_model.mapper.auth.token.details;

import com.niksob.authorization_model.dto.token.details.AuthTokenDetailsDto;
import com.niksob.authorization_model.model.token.AuthTokenDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthTokenDetailsDtoMapper {

    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "rowPassword.value", target = "rowPassword")
    AuthTokenDetailsDto toDto(AuthTokenDetails authTokenDetails);
}
