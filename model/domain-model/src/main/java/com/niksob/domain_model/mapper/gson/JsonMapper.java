package com.niksob.domain_model.mapper.gson;

public interface JsonMapper {
    <T> String toJson(T src);
}
