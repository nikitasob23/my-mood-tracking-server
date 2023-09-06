package com.niksob.gateway.service.auth.signup;

import com.niksob.authorization_model.dto.signup.SignupDetailsDto;
import com.niksob.authorization_model.model.signup.RowSignupDetails;
import com.niksob.gateway.client.HttpClient;
import com.niksob.authorization_model.mapper.auth.signup.SignupInfoDtoMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SignupServiceImpl implements SignupService {

    private final HttpClient httpClient;

    private final SignupInfoDtoMapper signupInfoDtoMapper;

    private final String authServiceUri;

    public SignupServiceImpl(
            @NonNull HttpClient httpClient,
            @NonNull SignupInfoDtoMapper signupInfoDtoMapper,
            @NonNull @Qualifier("signup_uri") String authServiceUri
    ) {
        this.httpClient = httpClient;
        this.signupInfoDtoMapper = signupInfoDtoMapper;
        this.authServiceUri = authServiceUri;
    }

    @Override
    public Mono<Void> execute(RowSignupDetails rowSignupDetails) {
        final SignupDetailsDto signupDetailsDto = signupInfoDtoMapper.toDto(rowSignupDetails);

        return httpClient.sendPostRequest(
                authServiceUri,
                signupDetailsDto, SignupDetailsDto.class
        );
    }
}
