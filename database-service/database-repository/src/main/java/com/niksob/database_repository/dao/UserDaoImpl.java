package com.niksob.database_repository.dao;

import com.niksob.database_repository.mapper.user.stored.UserInfoToStoredDtoMapper;
import com.niksob.database_repository.mapper.user.stored.login.username.UsernameToStoredDtoMapper;
import com.niksob.database_repository.storage.user.UserStorage;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.username.Username;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserStorage userStorage;
    private final UsernameToStoredDtoMapper usernameToStoredDtoMapper;
    private final UserInfoToStoredDtoMapper userInfoToStoredDtoMapper;

    @Override
    public UserInfo loadUserByUsername(Username username) {
        return Stream.of(username)
                .map(usernameToStoredDtoMapper::toDto)
                .map(userStorage::loadUserByUsername)
                .map(userInfoToStoredDtoMapper::fromDto)
                .findFirst().get();
    }

    @Override
    public Void saveUser(UserInfo userInfo) {
        Stream.of(userInfo)
                .map(userInfoToStoredDtoMapper::toDto)
                .forEach(userStorage::saveUser);
        return null;
    }
}
