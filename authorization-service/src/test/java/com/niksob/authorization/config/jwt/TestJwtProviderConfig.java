package com.niksob.authorization.config.jwt;

import com.niksob.authorization.jwt.provider.JwtProvider;
import com.niksob.authorization.model.jwt.Jwt;
import com.niksob.authorization.model.jwt.JwtParams;
import io.jsonwebtoken.Claims;
import jakarta.annotation.PostConstruct;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("mocked_jwt_provider")
public class TestJwtProviderConfig {
    @Autowired
    @Qualifier("test_access_jwt_params")
    private JwtParams accessJwtParams;
    @Autowired
    @Qualifier("test_access_jwt")
    private Jwt accessJwt;
    @MockBean(name = "access_jwt_provider")
    private JwtProvider accessJwtProvider;

    @Autowired
    @Qualifier("test_refresh_jwt_params")
    public JwtParams refreshJwtParams;
    @Autowired
    @Qualifier("test_refresh_jwt")
    private Jwt refreshJwt;
    @MockBean(name = "refresh_jwt_provider")
    private JwtProvider refreshJwtProvider;

    @PostConstruct
    public void setupMockedMethods() {
        setupMockedMethods(accessJwtProvider, accessJwtParams, accessJwt);
        setupMockedMethods(refreshJwtProvider, refreshJwtParams, refreshJwt);
    }

    private void setupMockedMethods(JwtProvider jwtProvider, JwtParams jwtParams, Jwt jwt) {
        Mockito.when(jwtProvider.generate(/*jwtParams*/Mockito.any())).thenReturn(jwt);
        Mockito.when(jwtProvider.getJwtParams(jwt)).thenReturn(jwtParams);
        Mockito.when(jwtProvider.validate(jwt)).thenReturn(true);

        final Claims mockedClaims = Mockito.mock(Claims.class);
        Mockito.when(jwtProvider.getClaims(jwt)).thenReturn(mockedClaims);
    }
}
