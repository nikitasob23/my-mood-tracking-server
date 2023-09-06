package com.niksob.database_controller.service.user.saving;

import com.niksob.database_repository.dao.UserDao;
import com.niksob.domain_model.model.user.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveUserByUsernameServiceImpl implements SaveUserByUsernameService {

    private final UserDao userDao;

    @Override
    public Void execute(UserInfo userInfo) {
        userDao.saveUser(userInfo);
        return null;
    }
}
