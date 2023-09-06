package com.niksob.authorization.service.user.encoding;

import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class EncodeLoginPasswordServiceImpl implements EncodeLoginPasswordService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupDetails execute(RowUserSignupDetails userRowSignupDetails) {
        return new SignupDetails(
                userRowSignupDetails.username(),
                userRowSignupDetails.nickname(),
                convertPassword(userRowSignupDetails),
                null
        );
    }

    @NotNull
    private EncodedPassword convertPassword(RowUserSignupDetails userRowSignupDetails) {
        return Stream.of(userRowSignupDetails)
                .map(RowUserSignupDetails::rowPassword)
                .map(RowPassword::value)
                .map(passwordEncoder::encode)
                .map(EncodedPassword::new)
                .findFirst().get();
    }
}
