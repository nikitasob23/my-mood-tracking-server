package com.niksob.database_repository.mapper.user.stored;

import com.niksob.database_repository.mapper.token.refresh.stored.RefreshTokenToStoredDtoMapper;
import com.niksob.database_repository.mapper.user.stored.active.ActiveUserDetailsToStoredDtoMapper;
import com.niksob.database_repository.mapper.user.stored.login.UserLoginInfoToStoredDtoMapper;
import com.niksob.database_repository.model.user.StoredUserInfoDto;
import com.niksob.domain_model.model.user.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        UserLoginInfoToStoredDtoMapper.class,
        ActiveUserDetailsToStoredDtoMapper.class,
        RefreshTokenToStoredDtoMapper.class
})
public interface UserInfoToStoredDtoMapper {

    StoredUserInfoDto toDto(UserInfo userInfo);

    UserInfo fromDto(StoredUserInfoDto storedUserInfoDto);
}
