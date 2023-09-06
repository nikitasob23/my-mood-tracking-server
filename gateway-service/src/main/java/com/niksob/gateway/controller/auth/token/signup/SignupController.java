package com.niksob.gateway.controller.auth.token.signup;

import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization_model.dto.token.AuthTokenDto;
import com.niksob.authorization_model.mapper.auth.signup.SignupDetailsDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.AuthTokenDtoMapper;
import com.niksob.authorization_model.mapper.auth.token.details.AuthTokenDetailsMapper;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.authorization_model.model.signup.RowSignupDetails;
import com.niksob.domain_model.exception.rest.status.HttpStatusException;
import com.niksob.gateway.service.auth.signup.SignupService;
import com.niksob.gateway.service.auth.signup.creation.CreateDefSignupInfoService;
import com.niksob.gateway.service.auth.token.generation.GenerateAuthTokenService;
import com.niksob.gateway.rest.path.controller.signup.SignupControllerPaths;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping(SignupControllerPaths.BASE_URI)
public class SignupController {

    private final CreateDefSignupInfoService createDefSignupInfoService;

    private final SignupService signupService;

    private final AuthTokenDetailsMapper authTokenDetailsMapper;

    private final GenerateAuthTokenService generateAuthTokenService;

    private final SignupDetailsDtoMapper signupDetailsDtoMapper;

    private final AuthTokenDtoMapper authTokenDtoMapper;

    @PostMapping
    public Mono<AuthTokenDto> signup(@RequestBody RowUserSignupDetailsDto rowUserSignupDetailsDto) {

        final RowSignupDetails rowSignupDetails = Stream.of(rowUserSignupDetailsDto)
                .map(signupDetailsDtoMapper::fromDto)
                .map(createDefSignupInfoService::execute)
                .findFirst().get();

        final RowLoginInDetails rowLoginInDetails = authTokenDetailsMapper.toRowLoginInDetails(rowSignupDetails);

        return Mono.just(rowSignupDetails)
                .map(signupService::execute)
                .then(generateAuthTokenService.execute(rowLoginInDetails)
                        .map(authTokenDtoMapper::toDto))
                .doOnError(this::convertErrorToHttpStatusException);
    }

    private void convertErrorToHttpStatusException(Throwable throwable) {
        throw new HttpStatusException(throwable.getMessage());
    }
}
