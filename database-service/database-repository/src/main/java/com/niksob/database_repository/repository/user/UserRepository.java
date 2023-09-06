package com.niksob.database_repository.repository.user;

import com.niksob.database_repository.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
