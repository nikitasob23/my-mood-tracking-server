package com.niksob.authorization.config.jwt;

import com.niksob.authorization.config.logger.message.LoggerMessageConfig;
import com.niksob.authorization.converter.jwt.jwt_params.claims.ClaimsToJwtParamsConverter;
import com.niksob.authorization.date.expiration.ExpirationDateProvider;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization.jwt.provider.JwtProviderImpl;
import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLogger;
import com.niksob.authorization.logger.jwt.provider.LoggableJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(LoggerMessageConfig.class)
public class RefreshJwtProviderConfig {

    @Autowired
    private ExpirationDateProvider expirationDateProvider;

    @Autowired
    private ClaimsToJwtParamsConverter claimsToJwtParamsConverter;

    @Autowired
    private LoggerMessageConfig loggerMessageConfig;

    @Value("${auth.jwt.refresh.secret}")
    private String secretKeyStr;

    @Primary
    @Bean("refresh_jwt_provider")
    public JwtProvider getLoggableRefreshJwtProvider(
            @Qualifier("jwt_provider_logger") ObjectStateLogger logger
    ) {
        return new LoggableJwtProvider(
                loggerMessageConfig,
                logger,
                getRefreshJwtProvider()
        );
    }

    @Bean("base_refresh_jwt_provider")
    public JwtProvider getRefreshJwtProvider() {
        return new JwtProviderImpl(
                SecretKeyDecoding.decodeKey(secretKeyStr),
                expirationDateProvider,
                claimsToJwtParamsConverter
        );
    }
}
