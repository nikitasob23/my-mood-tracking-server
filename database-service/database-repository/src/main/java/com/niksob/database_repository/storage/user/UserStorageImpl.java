package com.niksob.database_repository.storage.user;

import com.niksob.database_repository.mapper.user.UserEntityToStoredDtoMapper;
import com.niksob.database_repository.model.user.StoredUserInfoDto;
import com.niksob.database_repository.model.user.login.StoredUsernameDto;
import com.niksob.database_repository.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class UserStorageImpl implements UserStorage {

    private final UserRepository userRepository;
    private final UserEntityToStoredDtoMapper userEntityToStoredDtoMapper;

    @Override
    public StoredUserInfoDto loadUserByUsername(StoredUsernameDto username) {
        return Stream.of(username)
                .map(StoredUsernameDto::value)
                .map(userRepository::findByEmail)
                .map(userEntityToStoredDtoMapper::toDto)
                .findFirst().get();
    }

    @Override
    public void saveUser(StoredUserInfoDto storedUserInfoDto) {
        Stream.of(storedUserInfoDto)
                .map(userEntityToStoredDtoMapper::fromDto)
                .forEach(userRepository::save);
    }
}
