package com.niksob.authorization.converter.user.jwt;

import com.niksob.authorization.converter.Converter;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.domain_model.model.user.UserInfo;

public interface UserToJwtParamsConverter extends Converter<UserInfo, JwtParams> {
}
