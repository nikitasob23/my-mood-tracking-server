package com.niksob.database_repository.storage.user;

import com.niksob.database_repository.model.user.StoredUserInfoDto;
import com.niksob.database_repository.model.user.login.StoredUsernameDto;

public interface UserStorage {

    StoredUserInfoDto loadUserByUsername(StoredUsernameDto username);

    void saveUser(StoredUserInfoDto storedUserInfoDto);
}
