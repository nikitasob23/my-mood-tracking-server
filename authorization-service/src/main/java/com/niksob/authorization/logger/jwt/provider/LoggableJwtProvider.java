package com.niksob.authorization.logger.jwt.provider;

import com.niksob.authorization.config.logger.message.LoggerMessageConfig;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.domain_model.logger.app.stacktrace.object_state.ObjectStateLogger;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtParams;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

@AllArgsConstructor
public class LoggableJwtProvider implements JwtProvider {
    @NonNull
    private final LoggerMessageConfig loggerMessageConfig;

    @NonNull
    private final ObjectStateLogger log;

    @NonNull
    private final JwtProvider jwtProvider;

    @Override
    public Jwt generate(@NonNull JwtParams jwtParams) {
        try {
            return Stream.of(jwtParams)
                    .map(jwtProvider::generate)
                    .peek(jwt -> log.debug(
                            loggerMessageConfig.getSucceedJwtGenerationTemplate(),
                            jwtParams.getSubject()
                    ))
                    .findFirst().get();
        } catch (Exception ex) {
            log.errorWithStackTraceAndObjectState(
                    loggerMessageConfig.getUnknownErrorWithJwtGenerationTemplate(),
                    jwtParams,
                    ex.getStackTrace(),
                    jwtParams.getSubject()
            );
            throw ex;
        }
    }

    @Override
    public boolean validate(@NonNull Jwt jwt) {
        final boolean isValid = jwtProvider.validate(jwt);
        if (isValid) {
            final Claims claims = getClaims(jwt);
            log.debug(loggerMessageConfig.getJwtIsValidTemplate(), claims.getSubject());
            return true;
        }
        log.debug(loggerMessageConfig.getJwtIsNotValidTemplate(), jwt.value());
        return false;
    }

    @Override
    public Claims getClaims(@NotNull Jwt jwt) {

        try {
            return jwtProvider.getClaims(jwt);
        } catch (ExpiredJwtException ex) {
            log.debug(loggerMessageConfig.getExpiredJwtTokenTemplate(), jwt.value());
            throw ex;
        }
    }

    @Override
    public JwtParams getJwtParams(@NonNull Jwt jwt) {
        return jwtProvider.getJwtParams(jwt);
    }
}
