package com.niksob.domain_model.mapper.gson;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonMapperImpl implements JsonMapper {

    private final Gson gson;

    @Override
    public <T> String toJson(T src) {
        return String.format("\"%s\": %s", src.getClass().getSimpleName(), gson.toJson(src));
    }
}
