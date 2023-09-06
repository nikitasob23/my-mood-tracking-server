package com.niksob.authorization_model.mapper.auth.login;

import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupDetailsMapper {

    @Mapping(source = "username", target = "loginInfo.username")
    @Mapping(source = "encodedPassword", target = "loginInfo.encodedPassword")
    @Mapping(source = "roles", target = "loginInfo.roles")
    @Mapping(source = "nickname", target = "name")
    UserInfo toUserInfo(SignupDetails signupDetails);
}
