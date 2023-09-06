package com.niksob.database_repository.mapper.user.stored.login.username;

import com.niksob.database_repository.model.user.login.StoredUsernameDto;
import com.niksob.domain_model.model.user.login.username.Username;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsernameToStoredDtoMapper {

    StoredUsernameDto toDto(Username username);

    Username fromDto(StoredUsernameDto storedUsernameDto);
}
