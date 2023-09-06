package com.niksob.authorization_model.mapper.auth.signup;

import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupDetailsDtoMapper {

    @Mapping(source = "email", target = "username.value")
    @Mapping(source = "rowPassword", target = "rowPassword.value")
    RowUserSignupDetails fromDto(RowUserSignupDetailsDto dto);
}
