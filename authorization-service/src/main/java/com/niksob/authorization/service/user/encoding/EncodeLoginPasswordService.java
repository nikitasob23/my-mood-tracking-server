package com.niksob.authorization.service.user.encoding;

import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.authorization.service.ExecutableService;

public interface EncodeLoginPasswordService extends ExecutableService<RowUserSignupDetails, SignupDetails> {
}
