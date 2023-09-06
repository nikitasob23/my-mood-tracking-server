package com.niksob.authorization_model.mapper.auth.token.details;

import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.authorization_model.model.signup.RowSignupDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthTokenDetailsMapper {

    RowLoginInDetails toRowLoginInDetails(RowSignupDetails rowSignupDetails);
}
