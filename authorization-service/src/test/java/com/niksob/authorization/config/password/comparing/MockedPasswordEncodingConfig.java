package com.niksob.authorization.config.password.comparing;

import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import jakarta.annotation.PostConstruct;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
@Profile("mocked_password_encoder")
public class MockedPasswordEncodingConfig {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RowPassword rowPassword;
    @Autowired
    private EncodedPassword encodedPassword;

    @PostConstruct
    public void setupMockedMethods() {
        Mockito.when(passwordEncoder.encode(rowPassword.value())).thenReturn(encodedPassword.value());
        Mockito.when(passwordEncoder.matches(rowPassword.value(), encodedPassword.value())).thenReturn(true);
    }
}
