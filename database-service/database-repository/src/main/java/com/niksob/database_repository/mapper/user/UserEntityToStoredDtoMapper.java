package com.niksob.database_repository.mapper.user;

import com.niksob.database_repository.entity.user.UserEntity;
import com.niksob.database_repository.model.user.StoredUserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityToStoredDtoMapper {

    @Mapping(source = "email", target = "loginInfo.username.value")
    @Mapping(source = "encodedPassword", target = "loginInfo.encodedPassword.value")
    @Mapping(source = "roles", target = "loginInfo.roles")
    @Mapping(source = "active", target = "activeDetails.active")
    @Mapping(source = "nonLocked", target = "activeDetails.nonLocked")
    @Mapping(source = "creationDateTime", target = "activeDetails.creationDateTime")
    @Mapping(source = "lastVisitDateTime", target = "activeDetails.lastVisitDateTime")
    @Mapping(source = "refreshToken.token", target = "refreshToken.value")
    StoredUserInfoDto toDto(UserEntity userEntity);

    @Mapping(source = "loginInfo.username.value", target = "email")
    @Mapping(source = "loginInfo.encodedPassword.value", target = "encodedPassword")
    @Mapping(source = "loginInfo.roles", target = "roles")
    @Mapping(source = "activeDetails.active", target = "active")
    @Mapping(source = "activeDetails.nonLocked", target = "nonLocked")
    @Mapping(source = "activeDetails.creationDateTime", target = "creationDateTime")
    @Mapping(source = "activeDetails.lastVisitDateTime", target = "lastVisitDateTime")
    @Mapping(source = "refreshToken.value", target = "refreshToken.token")
    UserEntity fromDto(StoredUserInfoDto  storedUserInfoDto);
}
