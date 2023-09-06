package com.niksob.gateway.service.auth.signup;

import com.niksob.authorization_model.model.signup.RowSignupDetails;
import com.niksob.gateway.service.ExecutableService;
import reactor.core.publisher.Mono;

public interface SignupService extends ExecutableService<RowSignupDetails, Mono<Void>> {
}
