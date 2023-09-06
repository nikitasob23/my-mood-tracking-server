package com.niksob.domain_model.mapper.user.login.username;

import com.niksob.domain_model.dto.user.login.username.UsernameDto;
import com.niksob.domain_model.model.user.login.username.Username;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsernameDtoMapper {

    UsernameDto toDto(Username username);

    Username fromDto(UsernameDto usernameDto);
}
