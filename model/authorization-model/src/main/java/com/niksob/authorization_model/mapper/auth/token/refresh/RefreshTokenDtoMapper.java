package com.niksob.authorization_model.mapper.auth.token.refresh;

import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenDtoMapper {

    RefreshToken fromDto(RefreshTokenDto dto);

    RefreshTokenDto toDto(RefreshToken refreshToken);
}
