package com.niksob.authorization.config.logger.property;

import com.niksob.domain_model.config.property.yaml.YamlPropertySourcesPlaceholderConfigurerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:logger-messages.yml")
public class LoggerMessagesPropertiesConfig {

    private static final String PROPERTY_FILE_NAME = "logger-messages.yml";

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return YamlPropertySourcesPlaceholderConfigurerFactory.create(PROPERTY_FILE_NAME);
    }
}
