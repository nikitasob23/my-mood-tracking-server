package com.niksob.authorization_model.mapper.auth.login;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.model.login.UserLoginInDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLoginInDetailsDtoMapper {

    @Mapping(source = "username", target = "username.value")
    @Mapping(source = "rowPassword", target = "rowPassword.value")
    UserLoginInDetails fromDto(RowLoginInDetailsDto dto);
}
