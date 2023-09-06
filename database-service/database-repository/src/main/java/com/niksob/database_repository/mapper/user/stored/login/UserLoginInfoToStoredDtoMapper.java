package com.niksob.database_repository.mapper.user.stored.login;

import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.database_repository.model.user.login.StoredUserLoginInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLoginInfoToStoredDtoMapper {

    StoredUserLoginInfoDto toDto(LoginInfo loginInfo);

    LoginInfo fromDto(StoredUserLoginInfoDto storedLoginInfoDto);
}
