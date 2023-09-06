package com.niksob.authorization.converter.jwt;

import com.niksob.authorization.config.jwt.SecretKeyDecoding;
import com.niksob.authorization.converter.jwt.jwt_params.claims.ClaimsToJwtParamsConverterImpl;
import com.niksob.authorization.date.expiration.ExpirationDateProviderImpl;
import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization.jwt.provider.JwtProviderImpl;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtClaims;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.authorization.values.jwt.JwtKey;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

public class ClaimsToJwtParamsConverterTest {

    @Test
    public void testConversion() {

        final ClaimsToJwtParamsConverterImpl claimsToJwtParamsConverter =
                new ClaimsToJwtParamsConverterImpl(
                        JwtKey.SUBJECT_KEY,
                        JwtKey.EXPIRATION_KEY
                );

        final JwtProvider jwtProvider = new JwtProviderImpl(
                SecretKeyDecoding.decodeKey("zL1HB3Pch05AvfyBovxrf/kpF9t2m4NC9KJUjEp27s9J2jEG3ifiKCGylaZ8fDeoONSTJP/wAzKawB8F9rOMNg=="),
                new ExpirationDateProviderImpl(),
                claimsToJwtParamsConverter
        );

        final String username = "u";
        final Map<String, Object> claimsMap = Map.of(
                "ROLE_KEY", Role.USER.name()
        );
        int expirationInMinutes = 30;

        final JwtParams jwtParams = new JwtParams(username, new JwtClaims(claimsMap), expirationInMinutes);

        final Jwt jwt = jwtProvider.generate(jwtParams);
        final Claims claims = jwtProvider.getClaims(jwt);

        final JwtParams resultJwtParams = claimsToJwtParamsConverter.convert(claims);

        jwtParams.setExpirationInMinutes(jwtParams.getExpirationInMinutes() - 1); // A few seconds have already passed
        assertThat(resultJwtParams).usingDefaultComparator().isEqualTo(jwtParams);
    }
}
