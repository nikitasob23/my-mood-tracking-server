package com.niksob.gateway.mapper.user.security;

import com.niksob.gateway.dto.user.security.SecurityUserDetailsDto;
import com.niksob.gateway.model.user.security.SecurityUserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecurityUserDetailsDtoMapper {
    SecurityUserDetails fromDto(SecurityUserDetailsDto dto);
}
