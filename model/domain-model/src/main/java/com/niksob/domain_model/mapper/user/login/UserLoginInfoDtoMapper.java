package com.niksob.domain_model.mapper.user.login;

import com.niksob.domain_model.dto.user.login.LoginInfoDto;
import com.niksob.domain_model.model.user.login.LoginInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLoginInfoDtoMapper {

    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "encodedPassword.value", target = "password")
    LoginInfoDto toDto(LoginInfo dto);

    @Mapping(source = "username", target = "username.value")
    @Mapping(source = "password", target = "encodedPassword.value")
    LoginInfo fromDto(LoginInfoDto dto);
}
