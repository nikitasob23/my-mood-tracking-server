package com.niksob.database_repository.mapper.token.refresh.stored;

import com.niksob.database_repository.model.token.refresh.stored.StoredRefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenToStoredDtoMapper {

    StoredRefreshTokenDto toDto(RefreshToken refreshToken);

    RefreshToken fromDto(StoredRefreshTokenDto storedRefreshTokenDto);
}
