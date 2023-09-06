package com.niksob.authorization_model.mapper.auth.signup;

import com.niksob.authorization_model.dto.signup.SignupDetailsDto;
import com.niksob.authorization_model.model.signup.RowSignupDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupInfoDtoMapper {
    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "rowPassword.value", target = "rowPassword")
    SignupDetailsDto toDto(RowSignupDetails rowSignupDetails);
}
