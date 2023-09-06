package com.niksob.authorization.jwt.provider;

import com.niksob.authorization.converter.jwt.jwt_params.claims.ClaimsToJwtParamsConverter;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtParams;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtProviderTest {

    @Autowired
    @Qualifier("access_jwt_provider")
    private JwtProvider accessJwtProvider;
    @Autowired
    @Qualifier("test_access_jwt_params")
    private JwtParams accessJwtParams;

    @Autowired
    @Qualifier("refresh_jwt_provider")
    private JwtProvider refreshJwtProvider;
    @Autowired
    @Qualifier("test_refresh_jwt_params")
    private JwtParams refreshJwtParams;

    @Autowired
    private ClaimsToJwtParamsConverter claimsToJwtParamsConverter;

    @Test
    public void testAccessJwtProviderGeneration() {
        testJwtProviderGeneration(accessJwtProvider, accessJwtParams);
    }

    @Test
    public void testRefreshJwtProviderGeneration() {
        testJwtProviderGeneration(refreshJwtProvider, refreshJwtParams);
    }

    private void testJwtProviderGeneration(JwtProvider jwtProvider, JwtParams jwtParams) {

        final Jwt jwt = jwtProvider.generate(jwtParams);
        final JwtParams resultJwtParams = jwtProvider.getJwtParams(jwt);

        int expirationWithPassedTime = jwtParams.getExpirationInMinutes() - 1;
        jwtParams.setExpirationInMinutes(expirationWithPassedTime);

        assertThat(resultJwtParams).isEqualTo(jwtParams);

        final boolean jwtValidationResult = jwtProvider.validate(jwt);
        assertThat(jwtValidationResult).isTrue();

        final Claims claims = jwtProvider.getClaims(jwt);
        final JwtParams jwtParamsFromClaims = claimsToJwtParamsConverter.convert(claims);
        assertThat(jwtParamsFromClaims).isEqualTo(jwtParams);
    }

//    @Test
//    public void accessJwtProviderGenerationTest() {
//
//        final JwtParams testJwtParams = new JwtParams(
//                usernameStr,
//                new JwtClaims(Map.of(RoleKey.ROLES_KEY.name(), Role.USER.name())),
//                5
//        );
//
//        final Jwt jwt = accessJwtProvider.generate(testJwtParams);
//
//        final JwtParams jwtParams = accessJwtProvider.getJwtParams(jwt);
//
//        final JwtParams expectedJwtParams = new JwtParams(
//                testJwtParams.getSubject(),
//                testJwtParams.getClaims(),
//                testJwtParams.getExpirationInMinutes() - 1
//        );
//        assertThat(jwtParams).isEqualTo(expectedJwtParams);
//    }
//
//    @Test
//    public void refreshJwtProviderGenerationTest() {
//
//        final JwtParams testJwtParams = new JwtParams(
//                usernameStr,
//                new JwtClaims(),
//                30
//        );
//
//        final Jwt jwt = refreshJwtProvider.generate(testJwtParams);
//
//        final JwtParams jwtParams = refreshJwtProvider.getJwtParams(jwt);
//
//        final JwtParams expectedJwtParams = new JwtParams(
//                testJwtParams.getSubject(),
//                testJwtParams.getClaims(),
//                testJwtParams.getExpirationInMinutes() - 1
//        );
//
//        assertThat(jwtParams).isEqualTo(expectedJwtParams);
//    }
}
