package com.niksob.database_controller.controller.user;

import com.niksob.database_controller.exception.user.UserNotFoundException;
import com.niksob.database_controller.service.user.loading.LoadUserByUsernameService;
import com.niksob.database_controller.service.user.saving.SaveUserByUsernameService;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.dto.user.login.username.UsernameDto;
import com.niksob.domain_model.mapper.user.UserInfoDtoMapper;
import com.niksob.domain_model.mapper.user.login.username.UsernameDtoMapper;
import com.niksob.database_model.rest.path.controller.user.UserControllerPaths;
import com.niksob.domain_model.model.user.login.username.Username;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(UserControllerPaths.BASE_URI)
public class UserController {

    private final LoadUserByUsernameService loadUserByUsernameService;
    private final SaveUserByUsernameService saveUserByUsernameService;
    private final UsernameDtoMapper usernameDtoMapper;
    private final UserInfoDtoMapper userInfoDtoMapper;

    @GetMapping(UserControllerPaths.LOADING)
    public Mono<UserInfoDto> loadUserByUsername(
            @RequestParam() UsernameDto username
    ) {
        return Mono.just(username)
                .map(usernameDtoMapper::fromDto)
                .flatMap(loadUserByUsernameService::execute)
                .map(userInfoDtoMapper::toDto)
                .onErrorResume(this::monoWithException);
    }

    @PostMapping(UserControllerPaths.SAVING)
    public Mono<Void> saveUser(@RequestBody UserInfoDto userInfoDto) {
        return Mono.just(userInfoDto)
                .map(userInfoDtoMapper::fromDto)
                .doOnNext(saveUserByUsernameService::execute)
                .then()
                .onErrorResume(e -> Mono.error(
                        new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e))
                );
    }

    private Mono<? extends UserInfoDto> monoWithException(Throwable e) {
        return Mono.error(() -> {
            if (e instanceof UserNotFoundException) {
                return new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
            } else {
                return returnInternalServerException(e);
            }
        });
    }

    private ResponseStatusException returnInternalServerException(Throwable e) {
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error during loading user", e
        );
    }
}
