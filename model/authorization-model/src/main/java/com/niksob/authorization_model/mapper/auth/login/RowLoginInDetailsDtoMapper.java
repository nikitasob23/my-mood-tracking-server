package com.niksob.authorization_model.mapper.auth.login;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RowLoginInDetailsDtoMapper {

    @Mapping(source = "username", target = "username.value")
    @Mapping(source = "rowPassword", target = "rowPassword.value")
    RowLoginInDetails fromDto(RowLoginInDetailsDto dto);

    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "rowPassword.value", target = "rowPassword")
    RowLoginInDetailsDto toDto(RowLoginInDetails rowLoginInDetails);
}
