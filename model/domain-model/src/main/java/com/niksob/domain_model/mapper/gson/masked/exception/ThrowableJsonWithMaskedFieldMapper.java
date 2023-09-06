package com.niksob.domain_model.mapper.gson.masked.exception;

import com.niksob.domain_model.config.exception.message.TokenLoggerExceptionMessageConfig;
import com.niksob.domain_model.mapper.gson.JsonMapper;
import com.niksob.domain_model.mapper.gson.masked.JsonWithMaskedFieldMapperImpl;

public class ThrowableJsonWithMaskedFieldMapper extends JsonWithMaskedFieldMapperImpl {
    private final TokenLoggerExceptionMessageConfig config;

    public ThrowableJsonWithMaskedFieldMapper(
            String fieldName,
            String findFieldKeyValuePatternTemplate,
            String findFieldValueFromKeyValuePattern,
            String charMask,
            Integer numberOfNonMaskedCharFromEnd,
            JsonMapper jsonMapper,
            TokenLoggerExceptionMessageConfig config
    ) {
        super(
                fieldName,
                findFieldKeyValuePatternTemplate,
                findFieldValueFromKeyValuePattern,
                charMask,
                numberOfNonMaskedCharFromEnd,
                jsonMapper
        );
        this.config = config;
    }

    @Override
    protected String findTokenValue(String json) {
        final String tokenValue;
        try {
            tokenValue = super.findTokenValue(json);
        } catch (Exception e) {
            throw new IllegalStateException(config.getNotAppliedTokenMask(), e);
        }
        if (tokenValue == null) {
            throw new IllegalStateException(config.getNotFoundTokenValuePattern());
        }
        return tokenValue;
    }
}
