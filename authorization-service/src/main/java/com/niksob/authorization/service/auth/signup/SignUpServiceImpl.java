package com.niksob.authorization.service.auth.signup;

import com.niksob.authorization.service.user.creation.CreateDefUserByLoginDetailsService;
import com.niksob.authorization.service.user.encoding.EncodeLoginPasswordService;
import com.niksob.authorization.service.user.saving.SaveUserInfoService;
import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final SaveUserInfoService saveUserInfoService;

    private final EncodeLoginPasswordService encodeLoginPasswordService;

    private final CreateDefUserByLoginDetailsService createDefUserByLoginDetailsService;

    @Override
    public Void execute(RowUserSignupDetails rowUserSignupDetails) {
        Stream.of(rowUserSignupDetails)
                .map(encodeLoginPasswordService::execute)
                .map(createDefUserByLoginDetailsService::execute)
                .forEach(saveUserInfoService::execute);
        return null;
    }
}
