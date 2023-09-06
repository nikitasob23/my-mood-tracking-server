package com.niksob.gateway.service.auth.signup.creation;

import com.niksob.authorization_model.model.signup.RowSignupDetails;
import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import com.niksob.domain_model.model.user.login.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CreateDefSignupInfoServiceImpl implements CreateDefSignupInfoService {

    private final Set<Role> defRoles;

    @Override
    public RowSignupDetails execute(RowUserSignupDetails rowUserSignupDetails) {
        return new RowSignupDetails(
                rowUserSignupDetails.username(),
                rowUserSignupDetails.nickname(),
                rowUserSignupDetails.rowPassword(),
                defRoles
        );
    }
}
