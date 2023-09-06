package com.niksob.domain_model.config.mapper.json.masked;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "masked.json")
@Getter
@Setter
public class JsonMaskedParamsConfig {
    private String findFieldKeyValuePatternTemplate;
    private String findFieldValueFromKeyValuePattern;
    private String charMask;

    private String passwordFieldName;
    private Integer passwordNumberOfNonMaskedCharFromEnd;

    private String tokenFieldName;
    private Integer tokenNumberOfNonMaskedCharFromEnd;
}
