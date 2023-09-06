package com.niksob.authorization.jwt.provider;

import com.niksob.authorization.converter.jwt.jwt_params.claims.ClaimsToJwtParamsConverter;
import com.niksob.authorization.date.expiration.ExpirationDateProvider;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtClaims;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.authorization_model.exception.auth.token.AuthTokenException;
import com.niksob.authorization_model.exception.auth.token.ExpiredAuthTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

@AllArgsConstructor
public class JwtProviderImpl implements JwtProvider {

    private SecretKey secretKey;

    private ExpirationDateProvider expirationDateProvider;

    private ClaimsToJwtParamsConverter claimsToJwtParamsConverter;

    @Override
    public Jwt generate(@NotNull JwtParams jwtParams) {

        final Date expirationDate = Stream.of(jwtParams)
                .map(JwtParams::getExpirationInMinutes)
                .map(expirationDateProvider::getByMinutes)
                .findFirst().get();

        final JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(jwtParams.getSubject())
                .setExpiration(expirationDate)
                .signWith(secretKey);

        setClaimsToJwtBuilder(jwtParams, jwtBuilder);

        return Stream.of(jwtBuilder)
                .map(JwtBuilder::compact)
                .map(Jwt::new)
                .findFirst().get();
    }

    private void setClaimsToJwtBuilder(JwtParams jwtParams, JwtBuilder jwtBuilder) {
        Stream.of(jwtParams)
                .map(JwtParams::getClaims)
                .map(JwtClaims::getStorage)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .forEach(entry -> jwtBuilder.claim(entry.getKey(), entry.getValue()));
    }

    @Override
    public boolean validate(@NotNull Jwt jwt) {
        try {
            getClaims(jwt);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Claims getClaims(@NotNull Jwt jwt) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt.value())
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new ExpiredAuthTokenException(ex);
        } catch (Exception ex) {
            throw new AuthTokenException(ex);
        }
    }

    @Override
    public JwtParams getJwtParams(@NonNull Jwt jwt) {
        return Stream.of(jwt)
                .map(this::getClaims)
                .map(claimsToJwtParamsConverter::convert)
                .findFirst().get();
    }
}
