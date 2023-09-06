package com.niksob.authorization.converter.jwt.jwt_params.claims;

import com.niksob.authorization.model.jwt.JwtClaims;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.authorization.values.jwt.JwtKey;
import io.jsonwebtoken.Claims;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClaimsToJwtParamsConverterImpl implements ClaimsToJwtParamsConverter {

    private final JwtKey subjectJwtKey;

    private final JwtKey expirationJwtKey;

    public ClaimsToJwtParamsConverterImpl(JwtKey subjectJwtKey, JwtKey expirationJwtKey) {
        this.subjectJwtKey = subjectJwtKey;
        this.expirationJwtKey = expirationJwtKey;
    }

    @Override
    public JwtParams convert(Claims claims) {
        return new JwtParams(
                claims.getSubject(),
                convertClaims(claims),
                convertExpiration(claims.getExpiration())
        );
    }

    private int convertExpiration(Date date) {
        return Stream.of(date)
                .map(Date::toInstant)
                .map(this::toZonedDateTime)
                .map(ZonedDateTime::toLocalDateTime)
                .map(d -> Duration.between(LocalDateTime.now(), d))
                .map(Duration::toMinutes)
                .map(minutes -> (int) minutes.longValue())
                .findFirst().get();
    }

    private ZonedDateTime toZonedDateTime(Instant instant) {
        return instant.atZone(ZoneId.systemDefault());
    }

    private JwtClaims convertClaims(Claims claims) {
        final Map<String, Object> claimMap = claims.entrySet().stream()
                .filter(this::excludeSubjectKey)
                .filter(this::excludeExpirationKey)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new JwtClaims(claimMap);
    }

    private boolean excludeSubjectKey(Map.Entry<String, Object> claimEntry) {
        return Stream.of(claimEntry)
                .map(Map.Entry::getKey)
                .noneMatch(subjectJwtKey.getValue()::equals);
    }

    private boolean excludeExpirationKey(Map.Entry<String, Object> claimEntry) {
        return Stream.of(claimEntry)
                .map(Map.Entry::getKey)
                .noneMatch(expirationJwtKey.getValue()::equals);
    }
}
