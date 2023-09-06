package com.niksob.database_controller.service.user.loading;

import com.niksob.database_controller.exception.user.UserNotFoundException;
import com.niksob.database_repository.dao.UserDao;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.username.Username;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoadUserByUsernameServiceImpl implements LoadUserByUsernameService {

    private final UserDao userDao;

    @Override
    public Mono<UserInfo> execute(Username username) {
        try {
            return Mono.just(username)
                    .map(userDao::loadUserByUsername);
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User not found");
        }
    }
}
