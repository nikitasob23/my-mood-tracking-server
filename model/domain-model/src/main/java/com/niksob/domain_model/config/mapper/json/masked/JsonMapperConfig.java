package com.niksob.domain_model.config.mapper.json.masked;

import com.google.gson.Gson;
import com.niksob.domain_model.config.exception.message.TokenLoggerExceptionMessageConfig;
import com.niksob.domain_model.mapper.gson.JsonMapper;
import com.niksob.domain_model.mapper.gson.JsonMapperImpl;
import com.niksob.domain_model.mapper.gson.masked.exception.ThrowableJsonWithMaskedFieldMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@AllArgsConstructor
public class JsonMapperConfig {

    private final JsonMaskedParamsConfig jsonMaskedParamsConfig;

    private final TokenLoggerExceptionMessageConfig tokenLoggerExceptionMessageConfig;

    @Bean("base_json_mapper")
    public JsonMapper getBaseJsonMapper(Gson gson) {
        return new JsonMapperImpl(gson);
    }

    @Bean("masked_password_json_mapper")
    public JsonMapper getJsonWithMaskedPasswordMapper(
            @Qualifier("base_json_mapper") JsonMapper jsonMapper
    ) {
        return new ThrowableJsonWithMaskedFieldMapper(
                jsonMaskedParamsConfig.getPasswordFieldName(),
                jsonMaskedParamsConfig.getFindFieldKeyValuePatternTemplate(),
                jsonMaskedParamsConfig.getFindFieldValueFromKeyValuePattern(),
                jsonMaskedParamsConfig.getCharMask(),
                jsonMaskedParamsConfig.getPasswordNumberOfNonMaskedCharFromEnd(),
                jsonMapper,
                tokenLoggerExceptionMessageConfig
        );
    }

    @Bean("masked_token_json_mapper")
    public JsonMapper getJsonWithMaskedTokenMapper(@Qualifier("masked_password_json_mapper") JsonMapper jsonMapper) {
        return new ThrowableJsonWithMaskedFieldMapper(
                jsonMaskedParamsConfig.getTokenFieldName(),
                jsonMaskedParamsConfig.getFindFieldKeyValuePatternTemplate(),
                jsonMaskedParamsConfig.getFindFieldValueFromKeyValuePattern(),
                jsonMaskedParamsConfig.getCharMask(),
                jsonMaskedParamsConfig.getTokenNumberOfNonMaskedCharFromEnd(),
                jsonMapper,
                tokenLoggerExceptionMessageConfig
        );
    }

    @Primary
    @Bean("json_mapper")
    public JsonMapper getJsonMapper(@Qualifier("masked_token_json_mapper") JsonMapper jsonMapper) {
        return jsonMapper;
    }
}
