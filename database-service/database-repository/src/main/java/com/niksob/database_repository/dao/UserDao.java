package com.niksob.database_repository.dao;

import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.username.Username;

public interface UserDao {

    UserInfo loadUserByUsername(Username username);

    Void saveUser(UserInfo userInfo);
}
