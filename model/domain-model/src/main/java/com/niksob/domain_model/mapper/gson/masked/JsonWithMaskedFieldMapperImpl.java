package com.niksob.domain_model.mapper.gson.masked;

import com.niksob.domain_model.mapper.gson.JsonMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonWithMaskedFieldMapperImpl implements JsonWithMaskedFieldMapper {
    private final String fieldName;
    private final String findFieldKeyValuePatternTemplate;
    private final String findFieldValueFromKeyValuePattern;
    private final String charMask;
    private final Integer numberOfNonMaskedCharFromEnd;

    private final JsonMapper jsonMapper;

    @Override
    public <T> String toJson(T src) {
        return Stream.of(src)
                .map(jsonMapper::toJson)
                .map(this::maskToken)
                .findFirst().get();
    }

    private String maskToken(String json) {
        final String findFieldKeyValuePattern = String.format(findFieldKeyValuePatternTemplate, fieldName);

        return Stream.of(findFieldKeyValuePattern)
                .map(Pattern::compile)
                .map(pattern -> pattern.matcher(json))
                .filter(Matcher::find)
                .map(Matcher::group)
                .map(this::maskTokenKeyValue)
                .map(maskedTokenKeyValue -> json.replaceFirst(findFieldKeyValuePattern, maskedTokenKeyValue))
                .findFirst().orElse(json);
    }

    private String maskTokenKeyValue(String tokenKeyValue) {
        return Stream.of(tokenKeyValue)
                .map(this::findTokenValue)
                .map(this::maskTokenValue)
                .map(maskedTokenValue -> tokenKeyValue.replaceAll(findFieldValueFromKeyValuePattern, maskedTokenValue))
                .findFirst().get();
    }

    private String maskTokenValue(String tokenJson) {
        final int needToMaskedCharNumber = tokenJson.length() - numberOfNonMaskedCharFromEnd;
        return IntStream.range(0, tokenJson.length())
                .mapToObj(i -> i <= needToMaskedCharNumber ? charMask : tokenJson.charAt(i))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    protected String findTokenValue(String json) {
        return Stream.of(findFieldValueFromKeyValuePattern)
                .map(Pattern::compile)
                .map(pattern -> pattern.matcher(json))
                .filter(Matcher::find)
                .map(Matcher::group)
                .findFirst().orElse(null);
    }
}
