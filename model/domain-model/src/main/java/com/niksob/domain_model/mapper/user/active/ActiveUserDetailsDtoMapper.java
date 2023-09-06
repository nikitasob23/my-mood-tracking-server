package com.niksob.domain_model.mapper.user.active;

import com.niksob.domain_model.dto.user.active.ActiveUserDetailsDto;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActiveUserDetailsDtoMapper {

    ActiveUserDetailsDto toDto(ActiveUserDetails activeUserDetails);

    ActiveUserDetails fromDto(ActiveUserDetailsDto activeUserDetailsDto);
}
