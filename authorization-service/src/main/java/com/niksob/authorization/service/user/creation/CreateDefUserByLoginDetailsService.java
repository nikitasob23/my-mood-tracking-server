package com.niksob.authorization.service.user.creation;

import com.niksob.authorization.service.ExecutableService;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.UserInfo;

public interface CreateDefUserByLoginDetailsService extends ExecutableService<SignupDetails, UserInfo> {
}
