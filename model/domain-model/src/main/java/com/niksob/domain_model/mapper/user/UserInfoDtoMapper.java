package com.niksob.domain_model.mapper.user;

import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.mapper.user.active.ActiveUserDetailsDtoMapper;
import com.niksob.domain_model.mapper.user.login.UserLoginInfoDtoMapper;
import com.niksob.domain_model.model.user.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        UserLoginInfoDtoMapper.class,
        ActiveUserDetailsDtoMapper.class
})
public interface UserInfoDtoMapper {

    UserInfoDto toDto(UserInfo userInfo);

    UserInfo fromDto(UserInfoDto userInfoDto);
}
