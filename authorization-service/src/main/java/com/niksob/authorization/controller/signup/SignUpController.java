package com.niksob.authorization.controller.signup;

import com.niksob.authorization_model.exception.db.refresh_token.save.DatabaseException;
import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization.service.auth.signup.SignUpService;
import com.niksob.authorization_model.mapper.auth.signup.SignupDetailsDtoMapper;
import com.niksob.authorization_model.rest.path.controller.signup.SignupControllerPaths;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping(SignupControllerPaths.BASE_URI)
public class SignUpController {

    private final SignUpService signUpService;

    private final SignupDetailsDtoMapper signupDetailsDtoMapper;

    @PostMapping
    public Mono<Void> signup(@RequestBody RowUserSignupDetailsDto rowUserSignupDetailsDto) {

        try {
            Stream.of(rowUserSignupDetailsDto)
                    .map(signupDetailsDtoMapper::fromDto)
                    .forEach(signUpService::execute);

            return Mono.empty();
        } catch (DatabaseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect login information", e);

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal server error during sign up",
                    e
            );
        }
    }
}
