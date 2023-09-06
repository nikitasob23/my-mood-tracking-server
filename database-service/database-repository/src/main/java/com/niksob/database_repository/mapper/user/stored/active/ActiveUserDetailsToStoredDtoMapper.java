package com.niksob.database_repository.mapper.user.stored.active;

import com.niksob.database_repository.model.user.active.StoredActiveUserDetailsDto;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActiveUserDetailsToStoredDtoMapper {

    StoredActiveUserDetailsDto toDto(ActiveUserDetails activeUserDetails);

    ActiveUserDetails fromDto(StoredActiveUserDetailsDto storedActiveUserDetailsDto);
}
