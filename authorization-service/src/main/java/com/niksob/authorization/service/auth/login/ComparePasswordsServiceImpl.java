package com.niksob.authorization.service.auth.login;

import com.niksob.authorization.model.login.password.ComparedPasswords;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComparePasswordsServiceImpl implements ComparePasswordsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean execute(@NonNull ComparedPasswords comparedPasswords) {
        return passwordEncoder.matches(
                comparedPasswords.getRowPassword().value(),
                comparedPasswords.getEncodedPassword().value()
        );
    }
}
