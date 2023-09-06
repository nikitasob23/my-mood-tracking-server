package com.niksob.authorization_model.mapper.error;

import com.niksob.authorization_model.exception.response.ResponseStatusException;
import com.niksob.authorization_model.model.response.error.ErrorDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ErrorDetailsMapper {
    @Mapping(target = "status", expression = "java(ex.getHttpStatus().value())")
    @Mapping(source = "httpStatus.reasonPhrase", target = "error")
    ErrorDetails fromResponseStatusException(ResponseStatusException ex);
}
