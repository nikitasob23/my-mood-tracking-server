package com.niksob.authorization.servie.user.encoding;

import com.niksob.authorization.service.user.encoding.EncodeLoginPasswordService;
import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EncodeLoginPasswordServiceTest {

    @Autowired
    private EncodeLoginPasswordService encodeLoginPasswordService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RowUserSignupDetails userRowSignupDetails;
    @Autowired
    private RowPassword rowPassword;

    @Test
    public void testEncodingLoginPassword() {

        Stream.of(userRowSignupDetails)
                .map(encodeLoginPasswordService::execute)
                .map(SignupDetails::encodedPassword)
                .map(EncodedPassword::value)
                .map(passwordStr -> passwordEncoder.matches(rowPassword.value(), passwordStr))
                .forEach(passwordIsCorrect -> assertThat(passwordIsCorrect).isTrue());
    }
}
